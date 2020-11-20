package com.esgi.nova.adapters.exposed.port_implementation.choice_translations

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ChoiceTranslationMapper
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
import com.esgi.nova.adapters.exposed.port_implementation.persistence.IPersistenceGetAllById
import com.esgi.nova.adapters.exposed.repositories.choice_translations.ChoiceTranslationByChoiceAndLanguageRepository
import com.esgi.nova.adapters.exposed.repositories.choice_translations.ChoiceTranslationByLanguageRepository
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import com.google.inject.Inject
import java.util.*

class ChoiceTranslationByChoiceAndLanguagePersistence @Inject constructor(override val repository: ChoiceTranslationByChoiceAndLanguageRepository,
                                                                          mapper: ChoiceTranslationMapper, dbContext: DatabaseContext):
        BasePersistence<ChoiceTranslationKey<UUID>, ChoiceTranslationCmdDto<UUID>, ChoiceTranslationEntity, ChoiceTranslationDto>(repository, mapper, dbContext),
        IPersistenceGetAllById<ChoiceTranslationKey<UUID>, ChoiceTranslationDto, ChoiceTranslationEntity> {
}