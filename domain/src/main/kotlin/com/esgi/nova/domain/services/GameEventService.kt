package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.services.IGameEventService
import com.esgi.nova.ports.required.IGameEventPersistence
import com.google.inject.Inject

class GameEventService @Inject constructor(private val gameEventPersistence: IGameEventPersistence) :
    IGameEventService {
    override fun getAll() = gameEventPersistence.getAll()
}