package com.esgi.nova.domain.services

import com.esgi.nova.domain.services.service.BaseService
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventsId
import com.esgi.nova.ports.provided.services.IGameEventService
import com.esgi.nova.ports.required.IGameEventPersistence
import com.google.inject.Inject
import java.util.*

class GameEventService @Inject constructor(override val persistence: IGameEventPersistence) : BaseService<UUID, GameEventCmdDto, GameEventDto>(persistence),
    IGameEventService {
    override fun getAllFiltered(filter: GameEventsId) = persistence.getAllFiltered(filter)

}
