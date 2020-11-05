package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import java.util.*

interface IGamePersistence : IGetAll<GameDto>, ICreate<GameCmdDto, GameDto>, IGetOne<UUID, GameDto>,
    IGetAllTotal<GameDto>, IUpdateOne<GameCmdDto, UUID, GameDto> {
}