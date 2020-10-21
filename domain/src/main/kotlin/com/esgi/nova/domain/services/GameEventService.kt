package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.provided.services.IGameEventService
import com.esgi.nova.ports.required.IGameEventPersistence
import com.google.inject.Inject

class GameEventService @Inject constructor(private val gameEventPersistence: IGameEventPersistence) :
    IGameEventService {
    override fun getAll(query: Query) = gameEventPersistence.getAll(query)
    override fun create(element: GameEventCmdDto): GameEventDto? = gameEventPersistence.create(element)
}