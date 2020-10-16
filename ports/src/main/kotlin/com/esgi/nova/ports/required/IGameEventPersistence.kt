package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.GameEventDto

interface IGameEventPersistence {
    fun getAll(): List<GameEventDto>
}