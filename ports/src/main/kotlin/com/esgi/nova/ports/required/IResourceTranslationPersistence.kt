package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.required.translations.ITranslationPersistence
import java.util.*

interface IResourceTranslationPersistence :
        ITranslationPersistence<ResourceTranslationKey<UUID>, ResourceTranslationCmdDto<UUID>, ResourceTranslationDto> {
    fun getTotalByLanguages(pagination: IPagination, languageIds: List<UUID>): ITotalCollection<ResourceTranslationDto>
    fun getAllByResourceIdAndLanguageId(resourceId: UUID, languageId: UUID): Collection<ResourceTranslationDto>
    fun getAllByChoiceIdAndLanguageId(choiceId: UUID, languageId: UUID): Collection<ResourceTranslationDto>
    fun getOneDefault(resourceId: UUID): ResourceTranslationDto?
    fun getAllDefaultByChoiceId(choiceId: UUID): Collection<ResourceTranslationDto>
}