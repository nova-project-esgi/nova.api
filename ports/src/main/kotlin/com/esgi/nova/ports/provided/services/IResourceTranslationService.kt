package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationLanguageCodesCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationLanguageIdCmdDto
import java.util.*

interface IResourceTranslationService : IGetAll<ResourceTranslationDto>,
    ICreate<ResourceTranslationLanguageIdCmdDto, ResourceTranslationDto>,
    IGetOne<ResourceTranslationKey, ResourceTranslationDto>, IGetPage<ResourceTranslationDto> {
    fun createWithCodes(resourceTranslation: ResourceTranslationLanguageCodesCmdDto): ResourceTranslationDto?
    fun getOneWithCodes(resourceId: UUID, languageCodes: String): ResourceTranslationDto?
    fun getOneOrDefault(id: ResourceTranslationKey): ResourceTranslationDto?
    fun getAllByChoiceIdAndLanguageIdWithDefaults(choiceId: UUID, languageId: UUID): Collection<ResourceTranslationDto>
}