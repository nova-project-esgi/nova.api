package com.esgi.nova.query.event

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EventRepository : JpaRepository<Event, UUID> {

    fun findEventsByEventTranslationsTitleStartingWithAndEventTranslationsLanguageConcatenatedCodesStartingWith(
        title: String,
        concatenatedCodes: String,
        page: Pageable
    ): Page<Event>
}