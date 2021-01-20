package com.esgi.nova.query.game

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GameRepository : JpaRepository<Game, UUID> {
    @Query(
        value = "SELECT g FROM Game g WHERE g.isEnded = ?1 ORDER BY g.gameEvents.size DESC",
        countQuery = "SELECT COUNT(g) FROM Game g WHERE g.isEnded = ?1"
    )
    fun findAllByIsEndedOrderByEventsCountDesc(isEnded: Boolean, pageable: Pageable): Page<Game>

    @Query(
        value = "SELECT g FROM Game g WHERE g.isEnded = ?1 AND g.difficulty.id = ?2 ORDER BY g.gameEvents.size DESC",
        countQuery = "SELECT COUNT(g) FROM Game g WHERE g.isEnded = ?1 AND g.difficulty.id = ?2"
    )
    fun findAllByIsEndedAndDifficultyIdOrderByEventsCountDesc(
        isEnded: Boolean,
        difficultyId: UUID,
        pageable: Pageable
    ): Page<Game>


    @Query(
        value = "SELECT g FROM Game g WHERE  g.user.id = ?1  AND g.isEnded = ?2 "
    )
    fun findAllByUserIdAndIsEnded(userId: UUID, isEnded: Boolean): List<Game>

}