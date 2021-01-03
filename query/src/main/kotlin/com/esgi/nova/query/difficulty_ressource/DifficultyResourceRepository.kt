package com.esgi.nova.query.difficulty_ressource

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DifficultyResourceRepository : JpaRepository<DifficultyResource, DifficultyResourceId> {
    fun findAllByDifficultyId(difficultyId: UUID): List<DifficultyResource>
}