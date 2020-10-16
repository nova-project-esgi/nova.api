package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.services.IGameService
import com.esgi.nova.ports.required.IGamePersistence
import com.google.inject.Inject

class GameService @Inject constructor(private val gamePersistence: IGamePersistence) : IGameService {
    override fun getAll() = gamePersistence.getAll()
}