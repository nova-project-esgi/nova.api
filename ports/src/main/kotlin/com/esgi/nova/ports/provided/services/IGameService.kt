package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.GameDto

interface IGameService {
    fun getAll(): List<GameDto>
}