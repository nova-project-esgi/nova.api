package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.models.GameEventEntity
import org.jetbrains.exposed.sql.transactions.transaction

class GameEventRepository {
    fun getAll(): List<GameEventEntity> = transaction { GameEventEntity.all().toList() }
}