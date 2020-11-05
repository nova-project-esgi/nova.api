package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import java.util.*

interface IChoiceTranslationService : IGetAll<ChoiceTranslationDto>,
    IGetOne<ChoiceTranslationKey<UUID>, ChoiceTranslationDto>,
    ICreate<ChoiceTranslationCmdDto<UUID>, ChoiceTranslationDto>, IGetPage<ChoiceTranslationDto>,
    IUpdateOne<ChoiceTranslationCmdDto<UUID>, ChoiceTranslationKey<UUID>, ChoiceTranslationDto> {
    fun getOneOrDefault(id: ChoiceTranslationKey<UUID>): ChoiceTranslationDto?
    fun getAllByChoiceIdsAndLanguageIdWithDefaults(
        choiceIds: List<UUID>,
        languageId: UUID
    ): Collection<ChoiceTranslationDto>
}