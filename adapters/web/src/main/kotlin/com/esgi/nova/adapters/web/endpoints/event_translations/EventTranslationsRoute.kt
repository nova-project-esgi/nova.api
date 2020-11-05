package com.esgi.nova.adapters.web.endpoints.event_translations

import com.esgi.nova.adapters.web.domain.EventTranslationCmd
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IEventTranslationCodesService
import com.esgi.nova.ports.provided.services.IEventTranslationService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class EventTranslationsRoute @Inject constructor(
    application: Application,
    eventTranslationService: IEventTranslationService,
    eventTranslationCodesService: IEventTranslationCodesService
) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    post<EventTranslationLocation> { location ->
                        val eventTranslationForCreation = call.receive<EventTranslationCmd>()
                        val createdEventTranslation = eventTranslationCodesService.create(
                            EventTranslationCmdDto(
                                title = eventTranslationForCreation.title,
                                description = eventTranslationForCreation.description,
                                eventId = location.eId,
                                language = location.codes
                                )
                        )
                        createdEventTranslation?.let {
                            call.createdIn(location)
                        }
                    }
                    get<EventTranslationLocation> { location ->
                        val eventTranslation =
                            eventTranslationCodesService.getOne(EventTranslationKey(location.eId, location.codes))
                        eventTranslation?.let {
                            call.respond(eventTranslation)
                        }
                    }
                }
            }
        }
    }
}