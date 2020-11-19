package com.esgi.nova.adapters.web.endpoints.choices

import com.esgi.nova.adapters.web.domain.CustomContentType
import com.esgi.nova.adapters.web.domain.pagination.PageMetadata
import com.esgi.nova.adapters.web.endpoints.translated_choices.TranslatedChoiceLocation
import com.esgi.nova.adapters.web.endpoints.translated_choices.TranslatedChoicesLocation
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.ports.provided.dtos.choice.commands.TranslatedChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.commands.TranslatedChoiceWithResourcesCmdDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.choices.ITranslatedChoiceService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class TranslatedChoicesRoute @Inject constructor(
        application: Application,
        translatedChoiceService: ITranslatedChoiceService,
) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    contentType(CustomContentType.Application.TranslatedChoice) {
                        post("/choices") { location ->
                            val choiceForCreation = call.receive<TranslatedChoiceCmdDto>()
                            translatedChoiceService.createTranslatedChoice(choiceForCreation)
                                    ?.let { createdChoice ->
                                        call.createdIn(TranslatedChoiceLocation(createdChoice.id, createdChoice.languageCode))
                                    }

                            println(choiceForCreation)
                        }
                    }
                    contentType(CustomContentType.Application.TranslatedChoiceResource) {
                        post("/choices") {
                            val choiceForCreation = call.receive<TranslatedChoiceWithResourcesCmdDto>()
                            translatedChoiceService.createTranslatedChoiceAndAttachResources(
                                    choiceForCreation
                            )?.let { createdChoice ->
                                call.createdIn(TranslatedChoiceLocation(createdChoice.id, createdChoice.languageCode))
                            }
                        }
                    }
                }
                accept(CustomContentType.Application.TranslatedChoiceResource) {
                    get<TranslatedChoiceLocation> { location ->
                        translatedChoiceService.getTranslatedChoiceDetailed(
                                location.id,
                                location.language,
                                includeEvent = true,
                                includeResources = true, useDefaultLanguage = true
                        )?.let { choice ->
                            call.respond(choice)
                        }
                    }
                    get<TranslatedChoicesLocation> { location ->
                        translatedChoiceService.getTranslatedChoicesPage(
                                location,
                                location.language,
                                includeEvent = false,
                                includeResources = false
                        ).let { choices ->
                            call.respond(PageMetadata(choices, call.request.uri))
                        }
                    }
                }

            }

        }
    }
}
