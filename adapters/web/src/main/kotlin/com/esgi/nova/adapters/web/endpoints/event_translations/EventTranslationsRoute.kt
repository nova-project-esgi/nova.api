package com.esgi.nova.adapters.web.endpoints.event_translations

import com.esgi.nova.adapters.web.domain.EventTranslationCmd
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationLanguageCodesCmdDto
import com.esgi.nova.ports.provided.enums.Role
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
    eventTranslationService: IEventTranslationService
) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    post<EventTranslationLocation> { location ->
                        val eventTranslationForCreation = call.receive<EventTranslationCmd>()
                        val createdEventTranslation = eventTranslationService.createOneWithCodes(
                            EventTranslationLanguageCodesCmdDto(
                                eventTranslationForCreation.title,
                                eventTranslationForCreation.description,
                                location.codes,
                                location.eId
                            )
                        )
                        createdEventTranslation?.let {
                            call.createdIn(location)
                        }
                    }
                    get<EventTranslationLocation> { location ->
                        val eventTranslation =
                            eventTranslationService.getOneByEventIdAndLanguageCodes(location.eId, location.codes)
                        eventTranslation?.let {
                            call.respond(eventTranslation)
                        }
                    }
                }
            }
        }
    }
}