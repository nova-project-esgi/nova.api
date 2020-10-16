package com.esgi.nova.adapters.web.routes

import com.esgi.nova.ports.provided.dtos.EventDto
import com.esgi.nova.ports.provided.services.IEventService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.routing.*

class EventRoute @Inject constructor(application: Application, eventService: IEventService) {

    init {
        application.routing {
            route("/events") {
                authenticate {
                    post {
                        val eventDto = call.receive<EventDto>()
                        eventService.create(eventDto)
                    }
                }
            }

        }
    }
}