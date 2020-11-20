package com.esgi.nova.adapters.exposed.repositories.event_translations

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.IGetAllIterableById
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.adapters.exposed.tables.EventTranslation
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class EventTranslationByEventAndLanguageRepository :
        BaseEventTranslationRepository<EventTranslationKey<UUID>>(),
        IGetAllIterableById<EventTranslationKey<UUID>, EventTranslationEntity> {

    override fun getAllById(id: EventTranslationKey<UUID>): SizedIterable<EventTranslationEntity> = transaction {
        EventTranslationEntity.find { (EventTranslation.event eq id.entityId) and (EventTranslation.language eq id.language) }
    }
    override fun getOne(id: EventTranslationKey<UUID>): EventTranslationEntity? =
            transaction {
                EventTranslationEntity.find { (EventTranslation.event eq id.entityId) and (EventTranslation.language eq id.language) }
                        .firstOrNull()
            }

}