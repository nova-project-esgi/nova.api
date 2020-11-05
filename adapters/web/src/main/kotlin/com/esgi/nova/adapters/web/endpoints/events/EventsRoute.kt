package com.esgi.nova.adapters.web.endpoints.events

import com.esgi.nova.adapters.web.domain.pagination.PageMetadata
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.exceptHeaderNames
import com.esgi.nova.adapters.web.extensions.withHeaderNames
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventCmdDto
import com.esgi.nova.ports.provided.services.IEventService
import com.esgi.nova.ports.provided.services.ITranslatedEventService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class EventsRoute @Inject constructor(application: Application, eventService: IEventService, translatedEventService: ITranslatedEventService) {

    init {
        application.routing {
            authenticate {
                withHeaderNames(HttpHeaders.ContentLanguage) {
                    post("/events") {
                        val eventDto = call.receive<TranslatedEventCmdDto>()
                        val event = translatedEventService.createTranslatedEvent(
                            eventDto,
                            call.request.headers[HttpHeaders.ContentLanguage]!!
                        )
                        event?.let {
                            call.createdIn(EventLocation(id = event.id))
                        }
                    }
                }
                exceptHeaderNames(HttpHeaders.ContentLanguage) {
                    post("/events") {
                        val eventDto = call.receive<EventCmdDto>()
                        val event = eventService.create(eventDto)
                        event?.let {
                            call.createdIn(EventLocation(id = event.id))
                        }
                    }
                }
                withHeaderNames(HttpHeaders.AcceptLanguage) {
                    get<EventLocation> {
                        val event =
                            translatedEventService.getTranslatedEventDetailed(
                                it.id,
                                call.request.headers[HttpHeaders.AcceptLanguage]!!,
                                includeChoices = true,useDefaultLanguage = false,
                            )
                        event?.let {
                            call.respond(event)
                        }
                    }
                    get<EventsLocation> {
                        val eventsPage =
                            translatedEventService.getTranslatedEventsPage(
                                it,
                                call.request.headers[HttpHeaders.AcceptLanguage]!!,includeChoices = false
                            )
                        val meta = PageMetadata(eventsPage, call.request.uri)
                        try {
                            call.respond(meta)
                        } catch (e: Exception) {
                            println(e)
                        }
                    }
                }
                exceptHeaderNames(HttpHeaders.AcceptLanguage) {
                    get<EventLocation> {
                        val event = eventService.getOne(it.id)
                        event?.let {
                            call.respond(event)
                        }
                    }
                    get<EventsLocation> {
                        val eventsPage = eventService.getPage(it)
                        val meta = PageMetadata(eventsPage, call.request.uri)
                        try {
                            call.respond(meta)
                        } catch (e: Exception) {
                            println(e)
                        }
                    }
                }
            }

        }
    }
}