package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.adapters.exposed.tables.EventTranslation
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationLanguageIdCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class EventTranslationRepository {
    fun getAll(): SizedIterable<EventTranslationEntity> = transaction { EventTranslationEntity.all() }
    fun getOne(id: EventTranslationKey): EventTranslationEntity? =
        transaction {
            EventTranslationEntity.find { (EventTranslation.event eq id.eventId) and (EventTranslation.language eq id.languageId) }
                .firstOrNull()
        }

    fun create(eventTranslation: EventTranslationLanguageIdCmdDto): EventTranslationEntity? = transaction {
        EventTranslationEntity.new {
            description = eventTranslation.description
            title = eventTranslation.title
            event = EventEntity[eventTranslation.eventId]
            language = LanguageEntity[eventTranslation.languageId]
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
}