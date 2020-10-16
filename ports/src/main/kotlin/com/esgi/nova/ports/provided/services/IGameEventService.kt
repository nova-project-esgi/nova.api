package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.GameEventDto

interface IGameEventService {
    fun getAll(): List<GameEventDto>
}