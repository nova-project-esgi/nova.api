package com.esgi.nova.query.game_event

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GameEventRepository : JpaRepository<GameEvent, UUID> {
    fun findAllByEventIdAndGameId(eventId: UUID, gameId: UUID): List<GameEvent>
    fun findAllByGameId(gameId: UUID): List<GameEvent>
    fun findAllByGameIdOrderByLinkTimeDesc(gameId: UUID): List<GameEvent>
    fun findFirstByGameUserIdOrderByLinkTimeDesc(userId: UUID): GameEvent?
}