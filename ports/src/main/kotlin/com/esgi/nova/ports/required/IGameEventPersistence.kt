package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.IGetAllFiltered
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventsId
import java.util.*

interface IGameEventPersistence : ICrudPersistence<UUID, GameEventCmdDto, GameEventDto>,
    IGetAllFiltered<GameEventsId, GameEventDto> {
}