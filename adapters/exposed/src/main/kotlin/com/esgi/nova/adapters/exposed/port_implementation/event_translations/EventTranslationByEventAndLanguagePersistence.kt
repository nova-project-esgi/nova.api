package com.esgi.nova.adapters.exposed.port_implementation.event_translations

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.EventTranslationMapper
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
import com.esgi.nova.adapters.exposed.port_implementation.persistence.IPersistenceGetAllById
import com.esgi.nova.adapters.exposed.repositories.event_translations.EventTranslationByEventAndLanguageRepository
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.google.inject.Inject
import java.util.*

class EventTranslationByEventAndLanguagePersistence @Inject constructor(
        override val repository: EventTranslationByEventAndLanguageRepository,
        mapper: EventTranslationMapper,
        dbContext: DatabaseContext
) : BasePersistence<EventTranslationKey<UUID>, EventTranslationCmdDto<UUID>, EventTranslationEntity, EventTranslationDto>(repository, mapper, dbContext),
        IPersistenceGetAllById<EventTranslationKey<UUID>, EventTranslationDto, EventTranslationEntity> {

}