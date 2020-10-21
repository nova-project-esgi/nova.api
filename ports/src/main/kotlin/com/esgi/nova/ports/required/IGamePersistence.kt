package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import java.util.*

interface IGamePersistence : IGetAll<GameDto>, ICreate<GameCmdDto, GameDto>, IGetOne<UUID, GameDto>,
    IGetAllTotal<GameDto> {
}