package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.ChoiceTranslationKey
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationLanguageIdCmdDto
import java.util.*

interface IChoiceTranslationPersistence : IGetAll<ChoiceTranslationDto>,
    ICreate<ChoiceTranslationLanguageIdCmdDto, ChoiceTranslationDto>, IGetAllTotal<ChoiceTranslationDto>,
    IGetOne<ChoiceTranslationKey, ChoiceTranslationDto> {
    fun getTotalByLanguages(pagination: IPagination, languageIds: List<UUID>): ITotalCollection<ChoiceTranslationDto>
    fun getAllByChoiceIdAndLanguageId(choiceId: UUID, languageId: UUID): Collection<ChoiceTranslationDto>
    fun getAllByChoiceIdsAndLanguageId(choiceIds: List<UUID>, languageId: UUID): Collection<ChoiceTranslationDto>
    fun getAllDefaultByChoiceIds(choiceIds: List<UUID>): Collection<ChoiceTranslationDto>
    fun getOneDefault(choiceId: UUID): ChoiceTranslationDto?
}