package com.esgi.nova.application.uses_cases.resources

import com.esgi.nova.application.axon.queryPage
import com.esgi.nova.application.services.files.FileService
import com.esgi.nova.common.extensions.extractFileName
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.languages.exceptions.LanguagesNotFoundByIdsException
import com.esgi.nova.core_api.languages.queries.AllLanguagesExistsByIdsQuery
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.resource_translation.commands.CreateResourceTranslationCommand
import com.esgi.nova.core_api.resource_translation.commands.ResourceTranslationIdentifier
import com.esgi.nova.core_api.resource_translation.queries.FindPaginatedResourceByNameAndConcatenatedCodeQuery
import com.esgi.nova.core_api.resource_translation.views.ResourceTranslationNameView
import com.esgi.nova.core_api.resources.commands.CreateResourceCommand
import com.esgi.nova.core_api.resources.commands.DeleteResourceCommand
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import com.esgi.nova.core_api.resources.queries.FindPaginatedResourcesWithTranslationsConcatenatedCodesAndNameQuery
import com.esgi.nova.core_api.resources.queries.FindResourceByIdQuery
import com.esgi.nova.core_api.resources.views.ResourceView
import com.esgi.nova.core_api.resources.views.ResourceWithTranslationsView
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

    fun createResourceWithTranslations(resourceWithTranslations: ResourceWithTranslationsForEdition): UUID {
        val languageIds = resourceWithTranslations.translations.map { translation -> translation.languageId };
        val languagesExists = queryGateway.query(
            AllLanguagesExistsByIdsQuery(languageIds = languageIds.map { LanguageIdentifier(it.toString()) }),
            Boolean::class.java
        ).join()
        if (languagesExists) {
            commandGateway.sendAndWait<ResourceIdentifier>(CreateResourceCommand(ResourceIdentifier()))?.let { id ->
                resourceWithTranslations.translations.forEach { translation ->
                    commandGateway.sendAndWait<ResourceTranslationIdentifier>(
                        CreateResourceTranslationCommand(
                            ResourceTranslationIdentifier(
                                languageId = translation.languageId.toString(),
                                resourceId = id.identifier
                            ),
                            name = translation.name
                        )
                    )
                }
                return id.toUUID();
            }
        }
        throw LanguagesNotFoundByIdsException(languageIds)
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
        if (fileService.fileExists(id.toString(), resourcesDir)) {
            throw ResourceIconAlreadyExists()
        }
        fileService.uploadFile(
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
    ): PageBase<ResourceWithTranslationsView> {
        return queryGateway.queryPage<ResourceWithTranslationsView, FindPaginatedResourcesWithTranslationsConcatenatedCodesAndNameQuery>(
            FindPaginatedResourcesWithTranslationsConcatenatedCodesAndNameQuery(
                concatenatedCodes = language,
                name = name,
                page = page,
                size = size
            )
        ).join()
    }

    fun deleteOneResourceById(id: UUID): UUID {
        return this.commandGateway
            .sendAndWait<ResourceIdentifier>(DeleteResourceCommand(id = ResourceIdentifier(id.toString())))
            .toUUID()
    }
}