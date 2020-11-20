package com.esgi.nova.adapters.exposed.repositories.event_translations

import com.esgi.nova.adapters.exposed.domain.*
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

abstract class BaseEventTranslationRepository<ID> :
        IRepository<ID, EventTranslationCmdDto<UUID>, EventTranslationEntity>
{
    override fun getAll(): SizedIterable<EventTranslationEntity> = transaction { EventTranslationEntity.all() }
    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<EventTranslationEntity> = transaction {
        val elements = EventTranslationEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }
    override fun create(element: EventTranslationCmdDto<UUID>): EventTranslationEntity? = transaction {
        EventTranslationEntity.new {
            description = element.description
            title = element.title
            EventEntity.findById(element.eventId)
                    ?.let { eventEntity -> event = eventEntity }
            LanguageEntity.findById(element.language)?.let { languageEntity ->
                language = languageEntity
            }
        }
    }
    override fun updateOne(element: EventTranslationCmdDto<UUID>, id: ID): EventTranslationEntity? =
            transaction {
                getOne(id)?.also { eventTranslationEntity ->
                    eventTranslationEntity.description = element.description
                    eventTranslationEntity.title = element.title
                    EventEntity.findById(element.eventId)
                            ?.let { eventEntity -> eventTranslationEntity.event = eventEntity }
                    LanguageEntity.findById(element.language)?.let { languageEntity ->
                        eventTranslationEntity.language = languageEntity
                    }
                }
            }
}