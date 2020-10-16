package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.GameDto

interface IGamePersistence {
    fun getAll(): List<GameDto>
}