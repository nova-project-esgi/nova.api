package com.esgi.nova.query.difficulty

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DifficultyRepository : JpaRepository<Difficulty, UUID> {
    fun findAllByDifficultyTranslationsNameStartingWithAndDifficultyTranslationsLanguageConcatenatedCodesStartingWith(
        name: String,
        language: String,
        pageable: Pageable
    ): Page<Difficulty>
}