package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.models.GameEntity
import org.jetbrains.exposed.sql.transactions.transaction

class GameRepository {
    fun getAll(): List<GameEntity> = transaction { GameEntity.all().toList() }
}