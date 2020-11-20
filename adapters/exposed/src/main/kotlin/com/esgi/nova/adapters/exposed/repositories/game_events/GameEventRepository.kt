package com.esgi.nova.adapters.exposed.repositories.game_events

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.*
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class GameEventRepository :BaseGameEventRepository<UUID>(), IRepository<UUID, GameEventCmdDto, GameEventEntity> {

    override fun getOne(id: UUID): GameEventEntity? = transaction { GameEventEntity.findById(id) }

}