package com.esgi.nova.adapters.web.endpoints.choice_translations

import com.esgi.nova.adapters.web.domain.ChoiceTranslationCmd
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationLanguageCodesCmdDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IChoiceTranslationService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class ChoiceTranslationsRoute @Inject constructor(
    application: Application,
    choiceTranslationService: IChoiceTranslationService
) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    post<ChoiceTranslationLocation> { location ->
                        val choiceTranslation = call.receive<ChoiceTranslationCmd>()
                        choiceTranslationService.createWithCodes(
                            ChoiceTranslationLanguageCodesCmdDto(
                                choiceTranslation.title,
                                choiceTranslation.description,
                                location.cId,
                                location.languageCodes
                            )
                        )?.let { createdChoice ->
                            call.createdIn(location)
                        }
                    }
                    get<ChoiceTranslationLocation> { location ->
                        choiceTranslationService.getOneWithCodes(location.cId, location.languageCodes)
                            ?.let { choiceTranslation ->
                                call.respond(choiceTranslation)
                            }

                    }
                }
            }
        }
    }
}