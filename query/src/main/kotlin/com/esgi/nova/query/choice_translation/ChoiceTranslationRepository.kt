package com.esgi.nova.query.choice_translation

import org.springframework.data.jpa.repository.JpaRepository

interface ChoiceTranslationRepository : JpaRepository<ChoiceTranslation, ChoiceTranslationId> {
}