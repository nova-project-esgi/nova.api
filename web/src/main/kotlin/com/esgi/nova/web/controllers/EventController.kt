package com.esgi.nova.web.controllers

import com.esgi.nova.application.uses_cases.events.DetailedEventForEdition
import com.esgi.nova.application.uses_cases.events.EventsUseCases
import com.esgi.nova.core_api.events.views.DetailedEventView
import com.esgi.nova.core_api.events.views.EventTranslationTitleView
import com.esgi.nova.core_api.events.views.EventTranslationView
import com.esgi.nova.web.content_negociation.CustomMediaType
import com.esgi.nova.web.extensions.toPageMetadata
import com.esgi.nova.web.pagination.PageMetadata
import com.esgi.nova.web.pagination.PaginationDefault
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("events")
open class EventController constructor(
    private val eventsUseCases: EventsUseCases
) {

    @GetMapping("test")
    open fun testCreateEvent(){
        eventsUseCases.testCreateEvent(DetailedEventForEdition(listOf(), listOf()))
    }

    @GetMapping(produces = [CustomMediaType.Application.DetailedEvent])
    open fun getPaginatedTranslatedEventsByConcatenatedCodeAndTitle(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "language", required = true) concatenatedCode: String,
        @RequestParam(value = "title", required = true) title: String
    ): PageMetadata<DetailedEventView> {
        return eventsUseCases.getPaginatedTranslatedEventsByConcatenatedCodeAndTitle(
            page,
            size,
            concatenatedCode,
            title
        ).toPageMetadata()
    }

    @GetMapping("events/{id}", produces = [CustomMediaType.Application.EventTranslation])
    open fun getTranslationsByEventAndLanguages(
        @PathVariable id: UUID,
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "languageIds") languageIds: List<UUID>
    ): PageMetadata<EventTranslationView> {
        return eventsUseCases.getTranslationsByEventAndLanguages(id, page, size, languageIds).toPageMetadata()
    }


    @GetMapping(produces = [CustomMediaType.Application.EventTitle])
    open fun getPaginatedTranslatedEventTitlesByConcatenatedCodeAndTitle(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "language", required = true) concatenatedCode: String,
        @RequestParam(value = "title", required = true) title: String
    ): PageMetadata<EventTranslationTitleView> {
        return eventsUseCases.getPaginatedTranslatedEventTitlesByConcatenatedCodeAndTitle(
            page,
            size,
            concatenatedCode,
            title
        ).toPageMetadata()
    }

}
