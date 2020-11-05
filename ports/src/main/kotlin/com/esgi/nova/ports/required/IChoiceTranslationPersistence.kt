package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import java.util.*

interface IChoiceTranslationPersistence : IGetAll<ChoiceTranslationDto>,
    ICreate<ChoiceTranslationCmdDto<UUID>, ChoiceTranslationDto>, IGetAllTotal<ChoiceTranslationDto>,
    IGetOne<ChoiceTranslationKey<UUID>, ChoiceTranslationDto>,
    IUpdateOne<ChoiceTranslationCmdDto<UUID>, ChoiceTranslationKey<UUID>, ChoiceTranslationDto> {
    fun getTotalByLanguages(pagination: IPagination, languageIds: List<UUID>): ITotalCollection<ChoiceTranslationDto>
    fun getAllByChoiceIdAndLanguageId(choiceId: UUID, languageId: UUID): Collection<ChoiceTranslationDto>
    fun getAllByChoiceIdsAndLanguageId(choiceIds: List<UUID>, languageId: UUID): Collection<ChoiceTranslationDto>
    fun getAllDefaultByChoiceIds(choiceIds: List<UUID>): Collection<ChoiceTranslationDto>
    fun getOneDefault(choiceId: UUID): ChoiceTranslationDto?
}