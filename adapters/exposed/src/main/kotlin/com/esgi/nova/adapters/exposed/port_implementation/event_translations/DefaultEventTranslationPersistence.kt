package com.esgi.nova.adapters.exposed.port_implementation.event_translations

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.EventTranslationMapper
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.port_implementation.translation_persistence.BaseDefaultTranslationPersistence
import com.esgi.nova.adapters.exposed.repositories.event_translations.EventTranslationByEventAndLanguageRepository
import com.esgi.nova.adapters.exposed.repositories.event_translations.EventTranslationByLanguageRepository
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.required.ILanguagePersistence
import java.util.*

class DefaultEventTranslationPersistence(override val repository: EventTranslationByEventAndLanguageRepository,
                                         repositoryByLanguage: EventTranslationByLanguageRepository,
                                         languagePersistence: ILanguagePersistence,
                                         mapper: EventTranslationMapper, dbContext: DatabaseContext): BaseDefaultTranslationPersistence<
        EventTranslationKey<UUID>,
        EventTranslationCmdDto<UUID>,
        EventTranslationEntity, EventTranslationDto>(repository, repositoryByLanguage, languagePersistence, mapper, dbContext) {
    override fun toTranslationKey(entityId: UUID, languageId: UUID): EventTranslationKey<UUID> = EventTranslationKey(entityId, languageId)
}