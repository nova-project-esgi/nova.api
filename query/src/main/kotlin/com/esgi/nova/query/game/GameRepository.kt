package com.esgi.nova.query.game

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GameRepository : JpaRepository<Game, UUID> {
}