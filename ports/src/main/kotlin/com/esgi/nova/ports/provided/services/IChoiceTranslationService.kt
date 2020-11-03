package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.dtos.choice.ChoiceTranslationKey
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationLanguageCodesCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationLanguageIdCmdDto
import java.util.*

interface IChoiceTranslationService : IGetAll<ChoiceTranslationDto>,
    IGetOne<ChoiceTranslationKey, ChoiceTranslationDto>,
    ICreate<ChoiceTranslationLanguageIdCmdDto, ChoiceTranslationDto>, IGetPage<ChoiceTranslationDto> {
    fun createWithCodes(choiceTranslation: ChoiceTranslationLanguageCodesCmdDto): ChoiceTranslationDto?
    fun getOneWithCodes(choiceId: UUID, languageCodes: String): ChoiceTranslationDto?
    fun getOneOrDefault(id: ChoiceTranslationKey): ChoiceTranslationDto?
    fun getAllByChoiceIdsAndLanguageIdWithDefaults(
        choiceIds: List<UUID>,
        languageId: UUID
    ): Collection<ChoiceTranslationDto>
}