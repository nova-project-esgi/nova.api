package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventsId
import com.esgi.nova.ports.provided.services.IGameEventService
import com.esgi.nova.ports.required.IGameEventPersistence
import com.google.inject.Inject
import java.util.*

class GameEventService @Inject constructor(private val gameEventPersistence: IGameEventPersistence) :
    IGameEventService {
    override fun getAll() = gameEventPersistence.getAll()
    override fun create(element: GameEventCmdDto): GameEventDto? = gameEventPersistence.create(element)
    override fun getAllFiltered(filter: GameEventsId) = gameEventPersistence.getAllFiltered(filter)
    override fun getOne(id: UUID): GameEventDto? = gameEventPersistence.getOne(id)

}