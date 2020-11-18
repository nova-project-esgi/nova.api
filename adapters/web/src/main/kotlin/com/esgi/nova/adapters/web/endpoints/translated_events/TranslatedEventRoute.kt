package com.esgi.nova.adapters.web.endpoints.translated_events

import com.esgi.nova.adapters.web.domain.CustomContentType
import com.esgi.nova.adapters.web.domain.pagination.PageMetadata
import com.esgi.nova.adapters.web.endpoints.events.EventLocation
import com.esgi.nova.adapters.web.endpoints.events.EventsLocation
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.respondContentType
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventCmdDto
import com.esgi.nova.ports.provided.services.ITranslatedEventService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.json.simple.JSONObject

@KtorExperimentalLocationsAPI
class TranslatedEventRoute @Inject constructor(application: Application,translatedEventService: ITranslatedEventService) {

    init {
        application.routing {
            authenticate {
                contentType(CustomContentType.Application.TranslatedEvent) {
                    post("/events") {
                        val eventDto = call.receive<TranslatedEventCmdDto>()
                        val event = translatedEventService.createTranslatedEvent(
                                eventDto
                        )
                        event?.let {
                            call.createdIn(EventLocation(id = event.id))
                        }
                    }
                }
                accept(CustomContentType.Application.TranslatedEvent) {
                    get<TranslatedEventLocation> { location ->
                        val event =
                                translatedEventService.getTranslatedEventDetailed(
                                        location.id,
                                        location.language,
                                        includeChoices = true,useDefaultLanguage = false,
                                )
                        event?.let {
                            call.respond(event)
                        }
                    }
                    get<TranslatedEventsLocation> {location ->
                        val eventsPage =
                                translatedEventService.getTranslatedEventsPage(
                                        location,
                                        location.language,includeChoices = false
                                )
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