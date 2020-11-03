package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationLanguageIdCmdDto
import java.util.*

interface IResourceTranslationPersistence : IGetAll<ResourceTranslationDto>,
    ICreate<ResourceTranslationLanguageIdCmdDto, ResourceTranslationDto>,
    IGetAllTotal<ResourceTranslationDto>, IGetOne<ResourceTranslationKey, ResourceTranslationDto> {
    fun getTotalByLanguages(pagination: IPagination, languageIds: List<UUID>): ITotalCollection<ResourceTranslationDto>
    fun getAllByResourceIdAndLanguageId(resourceId: UUID, languageId: UUID): List<ResourceTranslationDto>
    fun getAllByChoiceIdAndLanguageId(choiceId: UUID, languageId: UUID): Collection<ResourceTranslationDto>
    fun getOneDefault(resourceId: UUID): ResourceTranslationDto?
    fun getAllDefaultByChoiceId(choiceId: UUID): Collection<ResourceTranslationDto>
}