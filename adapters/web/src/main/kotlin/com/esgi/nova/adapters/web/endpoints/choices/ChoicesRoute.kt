package com.esgi.nova.adapters.web.endpoints.choices

import com.esgi.nova.adapters.web.domain.CustomContentType
import com.esgi.nova.adapters.web.domain.pagination.PageMetadata
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.exceptHeaderNames
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.adapters.web.extensions.withHeaderNames
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceWithResourcesCmdDto
import com.esgi.nova.ports.provided.dtos.choice.commands.TranslatedChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.commands.TranslatedChoiceWithResourcesCmdDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IChoiceNavigationService
import com.esgi.nova.ports.provided.services.IChoiceService
import com.esgi.nova.ports.provided.services.ITranslatedChoiceService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class ChoicesRoute @Inject constructor(
        application: Application,
        choiceService: IChoiceService,
        choiceNavigationService: IChoiceNavigationService
) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    contentType(CustomContentType.Application.ChoiceWithResource) {
                        post("/choices") {
                            val choiceForCreation = call.receive<ChoiceWithResourcesCmdDto>()
                            val createdChoice = choiceService.createChoiceAndAttachResources(choiceForCreation)
                            createdChoice?.let {
                                call.createdIn(ChoiceLocation(createdChoice.id))
                            }
                        }
                    }
                    contentType(ContentType.Application.Json) {
                        post("/choices") {
                            val choiceForCreation = call.receive<ChoiceCmdDto>()
                            println(choiceForCreation)
                        }
                    }
                    accept(ContentType.Application.Json) {
                        get<ChoiceLocation> {
                            val choice = choiceService.getOne(it.id)
                            choice?.let {
                                call.respond(choice)
                            }
                        }
                        get<ChoicesLocation> { location ->
                            choiceService.getPage(location).let { choices ->
                                call.respond(PageMetadata(choices, call.request.uri))
                            }
                        }
                        
                    }
                    accept(CustomContentType.Application.ChoiceNavigation) {
                        get<ChoicesLocation> { location ->
                            choiceNavigationService.getPage(location).let { choices ->
                                call.respond(PageMetadata(choices, call.request.uri))
                            }
                        }
                    }
                }
            }
        }
    }
}
