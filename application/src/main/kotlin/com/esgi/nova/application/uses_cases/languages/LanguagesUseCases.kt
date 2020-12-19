package com.esgi.nova.application.uses_cases.languages

import com.esgi.nova.application.axon.queryPage
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.languages.commands.CreateLanguageCommand
import com.esgi.nova.core_api.languages.commands.DeleteLanguageCommand
import com.esgi.nova.core_api.languages.commands.UpdateLanguageCommand
import com.esgi.nova.core_api.languages.exceptions.LanguageAlreadyExistException
import com.esgi.nova.core_api.languages.exceptions.LanguageNotFoundByIdException
import com.esgi.nova.core_api.languages.queries.*
import com.esgi.nova.core_api.languages.queries.views.LanguageView
import com.esgi.nova.core_api.pagination.PageBase
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.util.*

@Service
open class LanguagesUseCases(private val commandGateway: CommandGateway, private val queryGateway: QueryGateway) {

    open fun create(language: LanguageForEdition): UUID {
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
                id = LanguageIdentifier(),
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

    fun deleteOneById(id: UUID) {
        if (!languageExists(id)) {
            throw LanguageNotFoundByIdException()
        }
        this.commandGateway.sendAndWait<DeleteLanguageCommand>(DeleteLanguageCommand(id = LanguageIdentifier(id.toString())))
    }

    fun languageExists(id: UUID): Boolean {
        return queryGateway
            .query<LanguageView, FindLanguageByIdQuery>(FindLanguageByIdQuery(LanguageIdentifier(id.toString())))
            .join() != null
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

    fun update(language: LanguageForEdition, id: UUID) {
        if (!languageExists(id)) {
            throw LanguageNotFoundByIdException()
        }
        this.commandGateway.sendAndWait<LanguageIdentifier>(
            UpdateLanguageCommand(
                id = LanguageIdentifier(id.toString()),
                code = language.code,
                subCode = language.subCode
            )
        )
    }
}