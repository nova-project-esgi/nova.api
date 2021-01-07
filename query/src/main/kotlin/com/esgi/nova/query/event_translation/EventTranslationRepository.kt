package com.esgi.nova.query.event_translation

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EventTranslationRepository : JpaRepository<EventTranslation, EventTranslationId> {

    fun findEventTranslationByLanguageCodeAndLanguageSubCodeAndTitleStartingWith(
        code: String,
        subCode: String?,
        title: String,
        pageable: Pageable
    ): Page<EventTranslation>

    fun findEventTranslationByEventIdAndLanguageIdIn(
        eventId: UUID,
        languageIds: List<UUID>,
        pageable: Pageable
    ): Page<EventTranslation>


    fun findEventTranslationByLanguageConcatenatedCodesAndTitleStartingWith(
        concatenatedCode: String,
        title: String,
        pageable: Pageable
    ): Page<EventTranslation>

}