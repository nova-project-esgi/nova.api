package com.esgi.nova.application.services.languages

import com.esgi.nova.application.extensions.queryPage
import com.esgi.nova.application.services.languages.exceptions.CantUpdateDefaultLanguageException
import com.esgi.nova.application.services.languages.exceptions.DefaultLanguageNotFoundException
import com.esgi.nova.application.services.languages.exceptions.LanguageAlreadyExistException
import com.esgi.nova.application.services.languages.exceptions.LanguageNotFoundByIdException
import com.esgi.nova.application.services.languages.models.LanguageForCreation
import com.esgi.nova.application.services.languages.models.LanguageForUpdate
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.languages.commands.CreateLanguageCommand
import com.esgi.nova.core_api.languages.commands.DeleteLanguageCommand
import com.esgi.nova.core_api.languages.commands.UpdateLanguageCommand
import com.esgi.nova.core_api.languages.commands.UpdateLanguageDefaultCommand
import com.esgi.nova.core_api.languages.queries.*
import com.esgi.nova.core_api.languages.views.LanguageView
import com.esgi.nova.core_api.languages.views.LanguageViewWithAvailableActions
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.resources.ResourceIdentifier
import com.esgi.nova.core_api.resources.commands.CanDeleteResourceTranslationCommand
import com.esgi.nova.core_api.resources.queries.FindAllResourcesWithTranslationIdsByLanguageIdQuery
import com.esgi.nova.core_api.resources.views.ResourceWithTranslationIdsView
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.extensions.kotlin.queryMany
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.util.*

@Service
open class LanguagesService(private val commandGateway: CommandGateway, private val queryGateway: QueryGateway) {

    fun getDefaultLanguageId(): UUID {
        return queryGateway.query<LanguageView?, FindDefaultLanguageQuery>(FindDefaultLanguageQuery()).join()?.id
            ?: throw DefaultLanguageNotFoundException()
    }

    open fun create(language: LanguageForCreation): UUID {
        val hasLanguage = queryGateway
            .query<LanguageView, FindLanguageByCodeAndSubCodeQuery>(
                FindLanguageByCodeAndSubCodeQuery(
                    code = language.code,
                    subCode = language.subCode
                )
            )
            .join() != null
        if (hasLanguage) {
            throw LanguageAlreadyExistException()
        }

        return commandGateway.sendAndWait<LanguageIdentifier>(
            CreateLanguageCommand(
                languageId = LanguageIdentifier(),
                subCode = language.subCode,
                code = language.code
            )
        ).toUUID()
    }


    open fun getAllPaginated(
        page: Int, size: Int
    ): PageBase<LanguageView> {
        return queryGateway
            .queryPage<LanguageView, FindPaginatedLanguagesQuery>(FindPaginatedLanguagesQuery(page, size))
            .join()
    }

    open fun getLanguageCount(): Int {
        return queryGateway
            .query(GetLanguageCountQuery(), Int::class.java)
            .join()
    }

    open fun getOneById(id: UUID): LanguageView? {
        return queryGateway
            .query<LanguageView, FindLanguageByIdQuery>(FindLanguageByIdQuery(LanguageIdentifier(id.toString())))
            .join()
    }

    open fun getPaginatedLanguagesByConcatenatedCode(
        page: Int,
        size: Int,
        concatenatedCode: String
    ): PageBase<LanguageView> {
        return queryGateway
            .queryPage<LanguageView, FindPaginatedLanguagesByConcatenatedCodeQuery>(
                FindPaginatedLanguagesByConcatenatedCodeQuery(
                    concatenatedCode,
                    page,
                    size
                )
            )
            .join()
    }


    fun canDelete(id: UUID) {
        if (!languageExists(id)) {
            throw LanguageNotFoundByIdException()
        }
        this.queryGateway.queryMany<ResourceWithTranslationIdsView, FindAllResourcesWithTranslationIdsByLanguageIdQuery>(
            FindAllResourcesWithTranslationIdsByLanguageIdQuery(languageId = LanguageIdentifier(id.toString()))
        ).join().forEach { resource ->
            commandGateway.sendAndWait(
                CanDeleteResourceTranslationCommand(
                    resourceId = ResourceIdentifier(resource.id.toString()),
                    translationId = LanguageIdentifier(id.toString())
                )
            )
        }

    }

    fun deleteOneById(id: UUID) {
        if (!languageExists(id)) {
            throw LanguageNotFoundByIdException()
        }
        this.commandGateway.send<LanguageIdentifier>(DeleteLanguageCommand(languageId = LanguageIdentifier(id.toString())))
    }

    fun languageExists(id: UUID): Boolean {
        return findLanguage(id) != null
    }

    fun findLanguage(id: UUID): LanguageView? {
        return queryGateway
            .query<LanguageView, FindLanguageByIdQuery>(FindLanguageByIdQuery(LanguageIdentifier(id.toString())))
            .join()
    }

    fun getPaginatedLanguagesByCodeAndSubCode(
        page: Int,
        size: Int,
        code: String,
        subCode: String?
    ): PageBase<LanguageView> {
        if (subCode == null) {
            return queryGateway.queryPage<LanguageView, FindPaginatedLanguagesByCodeQuery>(
                FindPaginatedLanguagesByCodeQuery(page = page, size = size, code = code)
            ).join()
        }
        return queryGateway.queryPage<LanguageView, FindPaginatedLanguagesByCodeAndSubCodeQuery>(
            FindPaginatedLanguagesByCodeAndSubCodeQuery(page = page, size = size, code = code, subCode = subCode)
        ).join()
    }

    fun getPaginatedLanguagesWithActionsByCodeAndSubCode(
        page: Int,
        size: Int,
        code: String,
        subCode: String?
    ): PageBase<LanguageViewWithAvailableActions> {
        if (subCode.isNullOrBlank()) {
            return queryGateway.queryPage<LanguageViewWithAvailableActions, FindPaginatedLanguagesWithAvailableActionsByCodeQuery>(
                FindPaginatedLanguagesWithAvailableActionsByCodeQuery(page = page, size = size, code = code)
            ).join()
        }
        return queryGateway.queryPage<LanguageViewWithAvailableActions, FindPaginatedLanguagesWithAvailableActionsByCodeAndSubCodeQuery>(
            FindPaginatedLanguagesWithAvailableActionsByCodeAndSubCodeQuery(
                page = page,
                size = size,
                code = code,
                subCode = subCode
            )
        ).join()
    }


    fun update(language: LanguageForUpdate, id: UUID) {
        val existingLanguage = findLanguage(id) ?: throw LanguageNotFoundByIdException()
        val languageCount = getLanguageCount()
        if (existingLanguage.isDefault && !language.isDefault) {
            if (languageCount == 1) {
                commandGateway.sendAndWait<LanguageIdentifier>(
                    UpdateLanguageDefaultCommand(
                        languageId = LanguageIdentifier(existingLanguage.id.toString()),
                        isDefault = language.isDefault
                    )
                )
                return
            } else {
                throw CantUpdateDefaultLanguageException()
            }
        }
        if (language.isDefault) {
            val defaultLanguage =
                queryGateway.query<LanguageView?, FindDefaultLanguageQuery>(FindDefaultLanguageQuery()).join()
            if (defaultLanguage?.id != id) {
                val canSetDefault = queryGateway.query(
                    CanSetLanguageDefaultQuery(LanguageIdentifier(id.toString())),
                    Boolean::class.java
                ).join()
                if (canSetDefault) {
                    defaultLanguage?.let {
                        commandGateway.sendAndWait<LanguageIdentifier>(
                            UpdateLanguageDefaultCommand(
                                languageId = LanguageIdentifier(defaultLanguage.id.toString()),
                                isDefault = false
                            )
                        )
                    }
                    commandGateway.sendAndWait<LanguageIdentifier>(
                        UpdateLanguageDefaultCommand(
                            languageId = LanguageIdentifier(id.toString()),
                            isDefault = true
                        )
                    )
                }
            }
        }
        this.commandGateway.sendAndWait<LanguageIdentifier>(
            UpdateLanguageCommand(
                languageId = LanguageIdentifier(id.toString()),
                code = language.code,
                subCode = language.subCode
            )
        )
    }

    fun getAll(): List<LanguageView> {
        return queryGateway.queryMany<LanguageView, GetAllLanguagesQuery>(GetAllLanguagesQuery()).join()
    }
}