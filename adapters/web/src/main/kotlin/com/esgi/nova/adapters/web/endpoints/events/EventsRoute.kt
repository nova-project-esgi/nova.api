package com.esgi.nova.adapters.web.endpoints.events

import com.esgi.nova.adapters.web.domain.pagination.PageMetadata
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.services.events.IEventService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class EventsRoute @Inject constructor(application: Application, eventService: IEventService) {

    init {
        application.routing {
            authenticate {
                contentType(ContentType.Application.Json) {
                    post("/events") {
                        val eventDto = call.receive<EventCmdDto>()
                        val event = eventService.create(eventDto)
                        event?.let {
                            call.createdIn(EventLocation(id = event.id))
                        }
                    }
                }
                accept(ContentType.Application.Json) {
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