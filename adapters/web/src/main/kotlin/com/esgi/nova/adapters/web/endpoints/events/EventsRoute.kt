package com.esgi.nova.adapters.web.endpoints.events

import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.services.IEventService
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
                    post("/events") {
                        val eventDto = call.receive<EventCmdDto>()
                        val event = eventService.create(eventDto)
                        event?.let {
                            val url = application.locations.href(EventLocation(id = event.id))
                            call.response.headers.append(HttpHeaders.Location, url)
                            call.respondText("Created")
                        }

                    }
                    get<EventsLocation> {
                        call.respond(eventService.getAll(Query(it)))
                    }
                    get<EventLocation> {
                        val event = eventService.getOne(it.id)
                        event?.let{
                            call.respond(event)
                        }

                    }
                }

        }
    }
}