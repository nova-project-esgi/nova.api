package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import java.util.*

interface IGameService : IGetAll<GameDto>, ICreate<GameCmdDto, GameDto>, IGetOne<UUID, GameDto>,
    IUpdateOne<GameCmdDto, UUID, GameDto> {
}