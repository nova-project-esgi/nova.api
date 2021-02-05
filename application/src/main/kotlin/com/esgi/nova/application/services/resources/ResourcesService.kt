package com.esgi.nova.application.services.resources

import com.esgi.nova.application.extensions.queryPage
import com.esgi.nova.application.pagination.Link
import com.esgi.nova.application.pagination.Relation
import com.esgi.nova.application.services.files.FileService
import com.esgi.nova.application.services.languages.LanguagesService
import com.esgi.nova.application.services.languages.exceptions.LanguagesNotFoundByIdsException
import com.esgi.nova.application.services.resources.exceptions.CantDeleteResourceException
import com.esgi.nova.application.services.resources.exceptions.NoDefaultLanguageResourceTranslationException
import com.esgi.nova.application.services.resources.exceptions.ResourceIconNotFoundException
import com.esgi.nova.application.services.resources.models.DetailedResourceForEdition
import com.esgi.nova.application.services.resources.models.TranslatedResourceWithIconDto
import com.esgi.nova.common.extensions.extractFileName
import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.languages.queries.AllLanguagesExistsByIdsQuery
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.resources.ResourceIdentifier
import com.esgi.nova.core_api.resources.commands.*
import com.esgi.nova.core_api.resources.dtos.ResourceDifficultyEditionDto
import com.esgi.nova.core_api.resources.dtos.ResourceTranslationEditionDto
import com.esgi.nova.core_api.resources.queries.*
import com.esgi.nova.core_api.resources.views.*
import com.esgi.nova.files.infra.UploadSettings
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.extensions.kotlin.queryMany
import org.axonframework.queryhandling.QueryGateway
import org.springframework.core.io.Resource
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.util.*

@Service
open class ResourcesService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val fileService: FileService,
    private val languageUsesCases: LanguagesService,
    private val uploadSettings: UploadSettings
) {


    fun createResourceWithTranslations(resourceWithTranslations: DetailedResourceForEdition): UUID {
        validateResourceWithTranslationsForEdition(resourceWithTranslations)
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
            resourceWithTranslations.difficulties.forEach { difficulty ->
                commandGateway.sendAndWait<LanguageIdentifier>(
                    CreateResourceDifficultyCommand(
                        resourceId = ResourceIdentifier(id.identifier),
                        difficultyId = DifficultyIdentifier(difficulty.difficultyId.toString()),
                        startValue = difficulty.startValue
                    )
                )
            }
            return id.toUUID();
        }
    }

    private fun validateResourceWithTranslationsForEdition(resourceWithTranslations: DetailedResourceForEdition) {
        val languageIds = resourceWithTranslations.translations.map { translation -> translation.languageId };
        val defaultLanguageId = languageUsesCases.getDefaultLanguageId()
        if (languageIds.none { it == defaultLanguageId }) {
            throw NoDefaultLanguageResourceTranslationException()
        }
        val languagesExists = queryGateway.query(
            AllLanguagesExistsByIdsQuery(languageIds = languageIds.map { LanguageIdentifier(it.toString()) }),
            Boolean::class.java
        ).join()
        if (!languagesExists) {
            throw LanguagesNotFoundByIdsException(languageIds)
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
            uploadSettings.resources,
            icon.originalFilename?.replace(icon.extractFileName(), id.toString())
        )
    }

    fun getResourceIcon(id: UUID): Resource {
        try {
            return this.fileService.loadFileAsResource(uploadSettings.resources, id.toString())
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
        return queryGateway.queryPage<ResourceTranslationNameView, FindPaginatedResourceTranslationNameViewsByNameAndConcatenatedCodeQuery>(
            FindPaginatedResourceTranslationNameViewsByNameAndConcatenatedCodeQuery(
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
        val canDelete =
            this.queryGateway.query(CanDeleteResourceByIdQuery(ResourceIdentifier(id.toString())), Boolean::class.java)
                .join()
        if (!canDelete) {
            throw CantDeleteResourceException()
        }
        this.commandGateway
            .sendAndWait<Any>(DeleteResourceCommand(resourceId = ResourceIdentifier(id.toString())))
    }

    fun updateResourceWithTranslations(id: UUID, resourceForUpdate: DetailedResourceForEdition) {
        this.commandGateway.sendAndWait<ResourceIdentifier>(
            UpdateResourceCommand(
                resourceId = ResourceIdentifier(id.toString()),
                translations = resourceForUpdate.translations.map {
                    ResourceTranslationEditionDto(
                        resourceId = ResourceIdentifier(id.toString()),
                        translationId = LanguageIdentifier(it.languageId.toString()),
                        name = it.name
                    )
                },
                difficulties = resourceForUpdate.difficulties.map {
                    ResourceDifficultyEditionDto(
                        resourceId = ResourceIdentifier(id.toString()),
                        startValue = it.startValue,
                        difficultyId = DifficultyIdentifier(it.difficultyId.toString())
                    )
                }
            )
        )
    }

    fun loadAllStandardResourcesByLanguage(language: String, baseIconUrl: String): List<TranslatedResourceWithIconDto> {
        return queryGateway.queryMany<TranslatedResourceView, FindAllTranslatedResourcesByLanguageQuery>(
            FindAllTranslatedResourcesByLanguageQuery(
                language = language
            )
        ).join().map { r ->
            TranslatedResourceWithIconDto(
                id = r.id,
                language = r.language,
                name = r.name,
                iconUrl = Link(Relation.ASSET, "$baseIconUrl/${r.id}/icon", HttpMethod.GET)
            )
        }
    }

    fun findAllResourcesByDifficulty(difficultyId: UUID): List<ResourceDifficultyView> {
        return queryGateway.queryMany<ResourceDifficultyView, FindAllResourcesByDifficultyIdQuery>(
            FindAllResourcesByDifficultyIdQuery(difficultyId = DifficultyIdentifier(difficultyId.toString()))
        ).join()
    }


}