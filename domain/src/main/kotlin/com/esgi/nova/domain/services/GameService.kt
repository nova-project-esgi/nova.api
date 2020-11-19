package com.esgi.nova.domain.services

import com.esgi.nova.domain.services.service.BaseService
import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.esgi.nova.ports.provided.services.IGameService
import com.esgi.nova.ports.required.IGamePersistence
import com.google.inject.Inject
import java.util.*

class GameService @Inject constructor(override val persistence: IGamePersistence)  : BaseService<UUID, GameCmdDto, GameDto>(persistence),
    IGameService {

}