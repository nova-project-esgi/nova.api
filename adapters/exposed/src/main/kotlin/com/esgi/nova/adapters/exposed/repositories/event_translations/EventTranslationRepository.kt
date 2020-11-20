package com.esgi.nova.adapters.exposed.repositories.event_translations

import com.esgi.nova.adapters.exposed.domain.*
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.adapters.exposed.tables.EventTranslation
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

open class EventTranslationRepository : BaseEventTranslationRepository<UUID>(), IRepository<UUID,
        EventTranslationCmdDto<UUID>, EventTranslationEntity> {

    //region inherited

    override fun getOne(id: UUID): EventTranslationEntity? =
            transaction {
                EventTranslationEntity.findById(id)
            }


    //endregion

}