package com.esgi.nova.adapters.exposed.port_implementation.event_translations

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.EventTranslationMapper
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.IPersistenceGetAllTotalById
import com.esgi.nova.adapters.exposed.port_implementation.persistence.IPersistenceGetAllTotalByIds
import com.esgi.nova.adapters.exposed.port_implementation.translation_persistence.BaseDefaultTranslationPersistence
import com.esgi.nova.adapters.exposed.repositories.event_translations.EventTranslationByEventAndLanguageRepository
import com.esgi.nova.adapters.exposed.repositories.event_translations.EventTranslationByLanguageRepository
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.required.IEventTranslationPersistence
import com.esgi.nova.ports.required.ILanguagePersistence
import com.google.inject.Inject
import java.util.*


open class EventTranslationPersistence  @Inject constructor(
        dbContext: DatabaseContext,
        override val repository: EventTranslationByEventAndLanguageRepository,
        val repositoryByLanguage: EventTranslationByLanguageRepository,
        mapper: EventTranslationMapper,
        languagePersistence: ILanguagePersistence
) : BaseDefaultTranslationPersistence<EventTranslationKey<UUID>, EventTranslationCmdDto<UUID>, EventTranslationEntity, EventTranslationDto>(
        repository,
        languagePersistence,
        mapper,
        dbContext
), IEventTranslationPersistence,
        IPersistenceGetAllTotalById<UUID, EventTranslationEntity, EventTranslationDto>,
        IPersistenceGetAllTotalByIds<UUID, EventTranslationEntity, EventTranslationDto> {

    override fun toTranslationKey(entityId: UUID, languageId: UUID): EventTranslationKey<UUID> = EventTranslationKey(entityId, languageId)
//    override fun getAllTotalById(pagination: IPagination, id: UUID): ITotalCollection<EventTranslationDto> = getAllTotalById(repositoryByLanguage, pagination, id)
//    override fun getAllTotalByIds(pagination: IPagination, ids: Collection<UUID>): ITotalCollection<EventTranslationDto> = getAllTotalByIds(repositoryByLanguage, pagination, ids)

}