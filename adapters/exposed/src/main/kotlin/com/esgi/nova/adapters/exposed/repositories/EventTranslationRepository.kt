package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.adapters.exposed.tables.EventTranslation
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class EventTranslationRepository {
    fun getAll(): SizedIterable<EventTranslationEntity> = transaction { EventTranslationEntity.all() }
    fun getOne(id: EventTranslationKey<UUID>): EventTranslationEntity? =
        transaction {
            EventTranslationEntity.find { (EventTranslation.event eq id.eventId) and (EventTranslation.language eq id.language) }
                .firstOrNull()
        }

    fun create(eventTranslation: EventTranslationCmdDto<UUID>): EventTranslationEntity? = transaction {
        EventTranslationEntity.new {
            description = eventTranslation.description
            title = eventTranslation.title
            EventEntity.findById(eventTranslation.eventId)
                ?.let { eventEntity -> event = eventEntity }
            LanguageEntity.findById(eventTranslation.language)?.let { languageEntity ->
                language = languageEntity
            }
        }
    }

    fun getAllTotal(pagination: DatabasePagination): ITotalCollection<EventTranslationEntity> = transaction {
        val elements = EventTranslationEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    fun getOne(eventId: UUID, languageId: UUID) = transaction {
        EventTranslationEntity.find { (EventTranslation.event eq eventId) and (EventTranslation.language eq languageId) }
            .singleOrNull()
    }

    fun getTotalByLanguages(pagination: DatabasePagination, languageIds: List<UUID>) = transaction {
        val elements = EventTranslationEntity.find { (EventTranslation.language inList languageIds) }
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    fun getAllByEventIdAndLanguageId(eventId: UUID, languageId: UUID): List<EventTranslationEntity> = transaction {
        EventTranslationEntity.find { (EventTranslation.event eq eventId) and (EventTranslation.language eq languageId) }
            .toList()
    }

    fun updateOne(eventTranslationId: EventTranslationKey<UUID>, eventTranslation: EventTranslationCmdDto<UUID>) =
        transaction {
            getOne(eventTranslationId)?.also { eventTranslationEntity ->
                eventTranslationEntity.description = eventTranslation.description
                eventTranslationEntity.title = eventTranslation.title
                EventEntity.findById(eventTranslation.eventId)
                    ?.let { eventEntity -> eventTranslationEntity.event = eventEntity }
                LanguageEntity.findById(eventTranslation.language)?.let { languageEntity ->
                    eventTranslationEntity.language = languageEntity
                }
            }
        }
}