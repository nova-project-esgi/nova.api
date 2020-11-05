package com.esgi.nova.domain.services

import com.esgi.nova.common.extensions.mergeDiff
import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.provided.services.IResourceTranslationService
import com.esgi.nova.ports.required.IResourceTranslationPersistence
import com.google.inject.Inject
import java.util.*

class ResourceTranslationService @Inject constructor(
    private val resourceTranslationPersistence: IResourceTranslationPersistence
) :
    IResourceTranslationService {

    override fun getAll(): Collection<ResourceTranslationDto> = resourceTranslationPersistence.getAll()

    override fun create(element: ResourceTranslationCmdDto<UUID>): ResourceTranslationDto? =
        resourceTranslationPersistence.create(element)

    override fun getOne(id: ResourceTranslationKey<UUID>): ResourceTranslationDto? = resourceTranslationPersistence.getOne(id)
    override fun getPage(pagination: IPagination): IPage<ResourceTranslationDto> =
        resourceTranslationPersistence.getAllTotal(pagination).toStaticPage(pagination)

    override fun updateOne(
        element: ResourceTranslationCmdDto<UUID>,
        id: ResourceTranslationKey<UUID>
    ): ResourceTranslationDto? = resourceTranslationPersistence.updateOne(element, id)

    override fun getOneOrDefault(id: ResourceTranslationKey<UUID>): ResourceTranslationDto? {
        return resourceTranslationPersistence.getOne(id)
            ?: resourceTranslationPersistence.getOneDefault(id.resourceId)
    }

    override fun getAllByChoiceIdAndLanguageIdWithDefaults(
        choiceId: UUID,
        languageId: UUID
    ): Collection<ResourceTranslationDto> {
        val resourceTranslations =
            resourceTranslationPersistence.getAllByChoiceIdAndLanguageId(choiceId, languageId).toMutableList()
        val defaultResourceTranslations = resourceTranslationPersistence.getAllDefaultByChoiceId(choiceId)
        if (defaultResourceTranslations.size > resourceTranslations.size) {
            resourceTranslations.mergeDiff(defaultResourceTranslations) { translation, defaultTranslation -> translation.resource.id == defaultTranslation.resource.id }

        }
        return resourceTranslations
    }
}