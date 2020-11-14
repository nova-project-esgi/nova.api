package com.esgi.nova.domain.services

import com.esgi.nova.common.extensions.mergeDiff
import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.services.IResourceTranslationService
import com.esgi.nova.ports.required.IResourceTranslationPersistence
import com.google.inject.Inject
import java.util.*

class ResourceTranslationService @Inject constructor(
    override val persistence: IResourceTranslationPersistence
) : BaseService<ResourceTranslationKey<UUID>, ResourceTranslationCmdDto<UUID>, ResourceTranslationDto>(persistence), IResourceTranslationService {

    override fun getOneOrDefault(id: ResourceTranslationKey<UUID>): ResourceTranslationDto? {
        return persistence.getOne(id)
            ?: persistence.getOneDefault(id.resourceId)
    }

    override fun getAllByChoiceIdAndLanguageIdWithDefaults(
        choiceId: UUID,
        languageId: UUID
    ): Collection<ResourceTranslationDto> {
        val resourceTranslations =
            persistence.getAllByChoiceIdAndLanguageId(choiceId, languageId).toMutableList()
        val defaultResourceTranslations = persistence.getAllDefaultByChoiceId(choiceId)
        if (defaultResourceTranslations.size > resourceTranslations.size) {
            resourceTranslations.mergeDiff(defaultResourceTranslations) { translation, defaultTranslation -> translation.resource.id == defaultTranslation.resource.id }

        }
        return resourceTranslations
    }
}