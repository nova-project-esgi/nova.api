package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetAllFiltered
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventsId
import java.util.*

interface IGameEventService : IGetAll<GameEventDto>, ICreate<GameEventCmdDto, GameEventDto>,
    IGetAllFiltered<GameEventsId, GameEventDto>, IGetOne<UUID, GameEventDto> {
}