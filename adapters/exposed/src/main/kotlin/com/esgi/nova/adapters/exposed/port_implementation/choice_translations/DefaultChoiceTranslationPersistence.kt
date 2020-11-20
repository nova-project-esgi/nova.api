package com.esgi.nova.adapters.exposed.port_implementation.choice_translations

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.domain.IRepositoryByLanguage
import com.esgi.nova.adapters.exposed.mappers.ChoiceTranslationMapper
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.port_implementation.translation_persistence.BaseDefaultTranslationPersistence
import com.esgi.nova.adapters.exposed.repositories.choice_translations.ChoiceTranslationByChoiceAndLanguageRepository
import com.esgi.nova.adapters.exposed.repositories.choice_translations.ChoiceTranslationByLanguageRepository
import com.esgi.nova.adapters.exposed.repositories.choice_translations.ChoiceTranslationRepository
import com.esgi.nova.ports.provided.dtos.ITranslationEntityKey
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import com.esgi.nova.ports.required.ILanguagePersistence
import java.util.*

class DefaultChoiceTranslationPersistence(override val repository: ChoiceTranslationByChoiceAndLanguageRepository,
                                          repositoryByLanguage: ChoiceTranslationByLanguageRepository,
                                          languagePersistence: ILanguagePersistence,
                                          mapper: ChoiceTranslationMapper, dbContext: DatabaseContext): BaseDefaultTranslationPersistence<
        ChoiceTranslationKey<UUID>,
        ChoiceTranslationCmdDto<UUID>,
        ChoiceTranslationEntity, ChoiceTranslationDto>(repository, repositoryByLanguage, languagePersistence, mapper, dbContext) {
    override fun toTranslationKey(entityId: UUID, languageId: UUID): ChoiceTranslationKey<UUID> = ChoiceTranslationKey(entityId, languageId)
}