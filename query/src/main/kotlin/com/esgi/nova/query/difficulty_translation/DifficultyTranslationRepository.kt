package com.esgi.nova.query.difficulty_translation

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface DifficultyTranslationRepository : JpaRepository<DifficultyTranslation, DifficultyTranslationId> {
    fun findAllByNameStartingWithAndLanguageConcatenatedCodesStartingWith(
        name: String,
        concatenatedCode: String,
        pageable: Pageable
    ): Page<DifficultyTranslation>
}