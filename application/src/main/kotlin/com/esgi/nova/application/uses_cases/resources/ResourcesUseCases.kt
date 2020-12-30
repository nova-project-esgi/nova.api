package com.esgi.nova.application.uses_cases.resources

import com.esgi.nova.application.axon.queryPage
import com.esgi.nova.application.services.files.FileService
import com.esgi.nova.common.extensions.extractFileName
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.languages.exceptions.DefaultLanguageNotFound
import com.esgi.nova.core_api.languages.exceptions.LanguagesNotFoundByIdsException
import com.esgi.nova.core_api.languages.queries.AllLanguagesExistsByIdsQuery
import com.esgi.nova.core_api.languages.queries.FindDefaultLanguageQuery
import com.esgi.nova.core_api.languages.queries.views.LanguageView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.resource_translation.commands.CreateResourceTranslationCommand
import com.esgi.nova.core_api.resource_translation.exceptions.NoDefaultLanguageResourceTranslation
import com.esgi.nova.core_api.resource_translation.queries.FindPaginatedResourceByNameAndConcatenatedCodeQuery
import com.esgi.nova.core_api.resource_translation.views.ResourceTranslationNameView
import com.esgi.nova.core_api.resources.commands.*
import com.esgi.nova.core_api.resources.queries.CanDeleteResourceByIdQuery
import com.esgi.nova.core_api.resources.queries.FindPaginatedResourcesWithTranslationsConcatenatedCodesAndNameQuery
import com.esgi.nova.core_api.resources.queries.FindPaginatedResourcesWithTranslationsQuery
import com.esgi.nova.core_api.resources.queries.FindResourceByIdQuery
import com.esgi.nova.core_api.resources.views.ResourceView
import com.esgi.nova.core_api.resources.views.ResourceWithAvailableActionsView
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.queryhandling.QueryGateway
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.util.*

@Service
open class ResourcesUseCases(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val fileService: FileService
) {

    private val resourcesDir = "resources"
    fun getDefaultLanguageId(): UUID {
        return queryGateway.query<LanguageView?, FindDefaultLanguageQuery>(FindDefaultLanguageQuery()).join()?.id
            ?: throw DefaultLanguageNotFound()
    }

    fun createResourceWithTranslations(resourceWithTranslations: ResourceWithTranslationsForEdition): UUID {
        val languageIds = resourceWithTranslations.translations.map { translation -> translation.languageId };
        val defaultLanguageId = getDefaultLanguageId()
        if (languageIds.none { it == defaultLanguageId }) {
            throw NoDefaultLanguageResourceTranslation()
        }
        val languagesExists = queryGateway.query(
            AllLanguagesExistsByIdsQuery(languageIds = languageIds.map { LanguageIdentifier(it.toString()) }),
            Boolean::class.java
        ).join()
        if (!languagesExists) {
            throw LanguagesNotFoundByIdsException(languageIds)
        }
        commandGateway.sendAndWait<ResourceIdentifier>(CreateResourceCommand(ResourceIdentifier())).let { id ->
            resourceWithTranslations.translations.forEach { translation ->
                commandGateway.sendAndWait<LanguageIdentifier>(
                    CreateResourceTranslationCommand(
                        resourceId = ResourceIdentifier(id.identifier),
                        translationId = LanguageIdentifier(translation.languageId.toString()),
                        name = translation.name
                    )
                )
            }
            return id.toUUID();
        }
    }


    fun getOneResourceById(resourceId: UUID): ResourceView {
        return queryGateway.query<ResourceView, FindResourceByIdQuery>(
            FindResourceByIdQuery(
                ResourceIdentifier(
                    resourceId.toString()
                )
            )
        ).join()
    }

    fun setResourceIcon(icon: MultipartFile, id: UUID) {
        fileService.setFile(
            icon,
            resourcesDir,
            icon.originalFilename?.replace(icon.extractFileName(), id.toString())
        )
    }

    fun getResourceIcon(id: UUID): Resource {
        try {
            return this.fileService.loadFileAsResource(resourcesDir, id.toString())
        } catch (e: FileNotFoundException) {
            throw ResourceIconNotFoundException()
        }
    }

    fun getPaginatedResourcesByNameLanguageCodeAndSubCode(
        page: Int,
        size: Int,
        name: String,
        language: String
    ): PageBase<ResourceTranslationNameView> {
        return queryGateway.queryPage<ResourceTranslationNameView, FindPaginatedResourceByNameAndConcatenatedCodeQuery>(
            FindPaginatedResourceByNameAndConcatenatedCodeQuery(
                name = name,
                concatenatedCode = language,
                page = page,
                size = size
            )
        ).join()
    }

    fun getPaginatedResourcesWithTranslationsByNameAndConcatenatedCodes(
        page: Int,
        size: Int,
        name: String,
        language: String
    ): PageBase<ResourceWithAvailableActionsView> {
        return queryGateway.queryPage<ResourceWithAvailableActionsView, FindPaginatedResourcesWithTranslationsConcatenatedCodesAndNameQuery>(
            FindPaginatedResourcesWithTranslationsConcatenatedCodesAndNameQuery(
                concatenatedCodes = language,
                name = name,
                page = page,
                size = size
            )
        ).join()
    }

    fun getPaginatedResourcesWithTranslations(
        page: Int,
        size: Int
    ): PageBase<ResourceWithAvailableActionsView> {
        return queryGateway.queryPage<ResourceWithAvailableActionsView, FindPaginatedResourcesWithTranslationsQuery>(
            FindPaginatedResourcesWithTranslationsQuery(
                page = page,
                size = size
            )
        ).join()
    }


    fun deleteOneResourceById(id: UUID) {
        val canDelete = this.queryGateway.query(CanDeleteResourceByIdQuery(ResourceIdentifier(id.toString())), Boolean::class.java).join()
        if(!canDelete){
            throw CantDeleteResourceException()
        }
        this.commandGateway
            .sendAndWait<Any>(DeleteResourceCommand(resourceId = ResourceIdentifier(id.toString())))
    }

    fun updateResourceWithTranslations(id: UUID, resourceForUpdate: ResourceWithTranslationsForEdition) {
        this.commandGateway.sendAndWait<ResourceIdentifier>(
            UpdateResourceCommand(
                resourceId = ResourceIdentifier(id.toString()),
                translations = resourceForUpdate.translations.map {
                    TranslationEditionDto(
                        resourceId = ResourceIdentifier(id.toString()),
                        translationId = LanguageIdentifier(it.languageId.toString()),
                        name = it.name
                    )
                }
            )
        )
    }


}