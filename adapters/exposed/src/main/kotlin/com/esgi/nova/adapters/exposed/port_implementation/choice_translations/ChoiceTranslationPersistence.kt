package com.esgi.nova.adapters.exposed.port_implementation.choice_translations

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ChoiceTranslationMapper
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.port_implementation.translation_persistence.BaseDefaultTranslationPersistence
import com.esgi.nova.adapters.exposed.repositories.choice_translations.ChoiceTranslationByChoiceAndLanguageRepository
import com.esgi.nova.ports.common.IGetAllById
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import com.esgi.nova.ports.required.IChoiceTranslationPersistence
import com.esgi.nova.ports.required.ILanguagePersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*


class ChoiceTranslationPersistence @Inject constructor(
        dbContext: DatabaseContext,
        override val repository: ChoiceTranslationByChoiceAndLanguageRepository,
        mapper: ChoiceTranslationMapper,
        languagePersistence: ILanguagePersistence
) : BaseDefaultTranslationPersistence<ChoiceTranslationKey<UUID>, ChoiceTranslationCmdDto<UUID>, ChoiceTranslationEntity, ChoiceTranslationDto>(
        repository,
        languagePersistence,
        mapper,
        dbContext
) ,IChoiceTranslationPersistence, IGetAllById<ChoiceTranslationKey<ChoiceTranslationKey<UUID>, ChoiceTranslationDto>{

    override fun toTranslationKey(entityId: UUID, languageId: UUID): ChoiceTranslationKey<UUID> = ChoiceTranslationKey(entityId, languageId)


    override fun getAllByChoiceIdsAndLanguageId(
            choiceIds: List<UUID>,
            languageId: UUID
    ): Collection<ChoiceTranslationDto> = execAndConvertElements {repository.getAllByChoiceIdsAndLanguageId(choiceIds, languageId)}

    override fun getAllDefaultByChoiceIds(choiceIds: List<UUID>): Collection<ChoiceTranslationDto> {
        languagePersistence.getDefault()?.let { language ->
            return getAllByChoiceIdsAndLanguageId(choiceIds, language.id)
        }
        return listOf()
    }

    override fun getTotalByTitleAndLanguage(pagination: IPagination, languageId: UUID, title: String): ITotalCollection<ChoiceTranslationDto> {
        TODO("Not yet implemented")
    }




}