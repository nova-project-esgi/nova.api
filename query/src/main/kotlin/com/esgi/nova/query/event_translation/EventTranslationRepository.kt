package com.esgi.nova.query.event_translation

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface EventTranslationRepository : PagingAndSortingRepository<EventTranslation, EventTranslationId> {

    fun findEventTranslationByLanguageCodeAndLanguageSubCodeAndTitleStartingWith(
        code: String,
        subCode: String?,
        title: String,
        pageable: Pageable
    ): Page<EventTranslation>

    fun findEventTranslationByEventIdAndLanguageIdIn(eventId: UUID, languageIds: List<UUID>, pageable: Pageable):Page<EventTranslation>

    fun findEventTranslationByLanguageCodeAndTitleStartingWith(
        code: String,
        title: String,
        pageable: Pageable
    ): Page<EventTranslation>
}