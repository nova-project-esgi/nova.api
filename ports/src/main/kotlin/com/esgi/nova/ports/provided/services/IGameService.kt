package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.esgi.nova.ports.required.ICreate
import com.esgi.nova.ports.required.IGetAll
import com.esgi.nova.ports.required.IGetOne
import java.util.*

interface IGameService: IGetAll<GameDto>, ICreate<GameCmdDto, GameDto>, IGetOne<UUID, GameDto> {
}