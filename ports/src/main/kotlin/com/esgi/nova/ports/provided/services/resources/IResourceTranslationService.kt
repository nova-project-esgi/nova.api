package com.esgi.nova.ports.provided.services.resources

import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.services.ICrudService
import java.util.*

interface IResourceTranslationService : ICrudService<ResourceTranslationKey<UUID>, ResourceTranslationCmdDto<UUID>, ResourceTranslationDto> {
    fun getOneOrDefault(id: ResourceTranslationKey<UUID>): ResourceTranslationDto?
    fun getAllByChoiceIdAndLanguageIdWithDefaults(choiceId: UUID, languageId: UUID): Collection<ResourceTranslationDto>
}