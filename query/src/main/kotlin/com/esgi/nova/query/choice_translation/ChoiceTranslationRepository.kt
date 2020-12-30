package com.esgi.nova.query.choice_translation

import com.esgi.nova.query.event_translation.EventTranslation
import org.springframework.data.jpa.repository.JpaRepository

interface ChoiceTranslationRepository: JpaRepository<ChoiceTranslation, ChoiceTranslationId > {
}