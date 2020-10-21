package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.required.ICreate
import com.esgi.nova.ports.required.IGetAll

interface IGameEventService: IGetAll<GameEventDto>, ICreate<GameEventCmdDto, GameEventDto> {
}