package com.esgi.nova.web.controllers

import com.esgi.nova.application.pagination.PageMetadata
import com.esgi.nova.application.pagination.PaginationDefault
import com.esgi.nova.application.services.events.EventsService
import com.esgi.nova.application.services.events.models.DetailedChoiceForEdition
import com.esgi.nova.application.services.events.models.DetailedChoiceForUpdate
import com.esgi.nova.application.services.events.models.DetailedEventForEdition
import com.esgi.nova.application.services.events.models.TranslatedEventsWithBackgroundDto
import com.esgi.nova.core_api.events.views.DetailedEventView
import com.esgi.nova.core_api.events.views.EventTranslationTitleView
import com.esgi.nova.core_api.events.views.EventTranslationView
import com.esgi.nova.core_api.events.views.EventView
import com.esgi.nova.web.content_negociation.CustomMediaType
import com.esgi.nova.web.extensions.buildSslAwareness
import com.esgi.nova.web.extensions.toPageMetadata
import com.esgi.nova.web.io.output.Message
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import java.util.*
import javax.servlet.ServletContext

@RestController
@RequestMapping("events")
open class EventController constructor(
    private val eventsService: EventsService,
    private val context: ServletContext
) {
    @GetMapping("{id}")
    fun getOneById(@PathVariable id: UUID): ResponseEntity<EventView> {
        return ResponseEntity.ok(eventsService.getOneById(id))
    }

    @GetMapping("{id}", produces = [CustomMediaType.Application.DetailedEvent])
    fun getOneDetailedById(@PathVariable id: UUID): ResponseEntity<DetailedEventView> {
        return ResponseEntity.ok(eventsService.getOneDetailedById(id))
    }

    @GetMapping(produces = [CustomMediaType.Application.DetailedEvent])
    open fun getPaginatedTranslatedEventsByConcatenatedCodeAndTitle(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "language", required = true) concatenatedCode: String,
        @RequestParam(value = "title", required = true) title: String
    ): PageMetadata<DetailedEventView> {
        return eventsService.getPaginatedTranslatedEventsByConcatenatedCodeAndTitle(
            page,
            size,
            concatenatedCode,
            title
        ).toPageMetadata()
    }

    @DeleteMapping("{id}")
    open fun deleteEvent(@PathVariable id: UUID): ResponseEntity<Any> {
        eventsService.deleteOneEventById(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping(consumes = [CustomMediaType.Application.DetailedEvent])
    open fun createDetailedEvent(@RequestBody event: DetailedEventForEdition<DetailedChoiceForEdition>): ResponseEntity<Message> {
        val id = eventsService.createEvent(event)
        return ResponseEntity
            .created(
                MvcUriComponentsBuilder.fromMethodName(EventController::class.java, "getOneById", id).buildSslAwareness().toUri()
            )
            .build()
    }

    @PutMapping("{id}", consumes = [CustomMediaType.Application.DetailedEvent])
    open fun updateDetailedEvent(
        @PathVariable id: UUID,
        @RequestBody event: DetailedEventForEdition<DetailedChoiceForUpdate>
    ): ResponseEntity<Any> {
        eventsService.updateEvent(id, event)
        return ResponseEntity
            .noContent()
            .build()
    }

    @GetMapping("events/{id}", produces = [CustomMediaType.Application.EventTranslation])
    open fun getTranslationsByEventAndLanguages(
        @PathVariable id: UUID,
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "languageIds") languageIds: List<UUID>
    ): PageMetadata<EventTranslationView> {
        return eventsService.getTranslationsByEventAndLanguages(id, page, size, languageIds).toPageMetadata()
    }


    @GetMapping(produces = [CustomMediaType.Application.EventTitle])
    open fun getPaginatedTranslatedEventTitlesByConcatenatedCodeAndTitle(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "language", required = true) concatenatedCode: String,
        @RequestParam(value = "title", required = true) title: String
    ): PageMetadata<EventTranslationTitleView> {
        return eventsService.getPaginatedTranslatedEventTitlesByConcatenatedCodeAndTitle(
            page,
            size,
            concatenatedCode,
            title
        ).toPageMetadata()
    }

    @PostMapping("{id}/background")
    fun setBackground(@RequestParam("file") file: MultipartFile, @PathVariable id: UUID): ResponseEntity<Any> {
        this.eventsService.setEventBackground(file, id);
        return ResponseEntity
            .created(
                MvcUriComponentsBuilder.fromMethodName(EventController::class.java, "getBackground", id).buildSslAwareness().toUri()
            )
            .build()
    }

    @GetMapping("{id}/background")
    fun getBackground(@PathVariable id: UUID): ResponseEntity<Resource> {

        val background = eventsService.getEventBackground(id)
        val contentType = context.getMimeType(background.file.absolutePath)
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + background.filename.toString() + "\""
            )
            .body<Resource>(background)
    }

    @GetMapping("load", produces = [CustomMediaType.Application.TranslatedEvent])
    fun loadAllStandardTranslatedEvents(
        @RequestParam(
            value = "language",
            required = true
        ) language: String
    ): ResponseEntity<List<TranslatedEventsWithBackgroundDto>> {
        return ResponseEntity.ok(
            eventsService.loadAllStandardEventsByLanguage(
                language,
                MvcUriComponentsBuilder.fromController(EventController::class.java).toUriString()
            )
        )
    }

    @GetMapping("load/{eventId}", produces = [CustomMediaType.Application.TranslatedEvent])
    fun loadOneTranslatedEventById(
        @PathVariable eventId: UUID,
        @RequestParam(
            value = "language",
            required = true
        ) language: String
    ): ResponseEntity<TranslatedEventsWithBackgroundDto> {
        val event = eventsService.loadOneStandardEventsByLanguage(
            language,
            eventId,
            MvcUriComponentsBuilder.fromController(EventController::class.java).toUriString()
        )
        if (event != null) {
            return ResponseEntity.ok(event)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

}
