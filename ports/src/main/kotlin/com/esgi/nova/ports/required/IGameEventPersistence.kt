package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto

interface IGameEventPersistence: IGetAll<GameEventDto>, ICreate<GameEventCmdDto, GameEventDto>,
    IGetAllTotal<GameEventDto> {
}