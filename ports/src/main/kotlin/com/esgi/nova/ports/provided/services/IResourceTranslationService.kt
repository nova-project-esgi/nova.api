package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import java.util.*

interface IResourceTranslationService : IGetAll<ResourceTranslationDto>,
    ICreate<ResourceTranslationCmdDto<UUID>, ResourceTranslationDto>,
    IGetOne<ResourceTranslationKey<UUID>, ResourceTranslationDto>, IGetPage<ResourceTranslationDto>,
    IUpdateOne<ResourceTranslationCmdDto<UUID>, ResourceTranslationKey<UUID>, ResourceTranslationDto> {
    fun getOneOrDefault(id: ResourceTranslationKey<UUID>): ResourceTranslationDto?
    fun getAllByChoiceIdAndLanguageIdWithDefaults(choiceId: UUID, languageId: UUID): Collection<ResourceTranslationDto>
}