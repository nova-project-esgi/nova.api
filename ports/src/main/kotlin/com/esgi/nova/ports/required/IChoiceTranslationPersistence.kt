package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.ITranslationEntityKey
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import java.util.*

interface IChoiceTranslationPersistence: ICrudPersistence<ChoiceTranslationKey<UUID>, ChoiceTranslationCmdDto<UUID>, ChoiceTranslationDto>{
    fun getAllByChoiceIdAndLanguageId(choiceId: UUID, languageId: UUID): Collection<ChoiceTranslationDto>
    fun getAllByChoiceIdsAndLanguageId(choiceIds: List<UUID>, languageId: UUID): Collection<ChoiceTranslationDto>
    fun getAllDefaultByChoiceIds(choiceIds: List<UUID>): Collection<ChoiceTranslationDto>
    fun getTotalByTitleAndLanguage(pagination: IPagination, languageId: UUID, title: String): ITotalCollection<ChoiceTranslationDto>
}