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
    translatedChoiceService: ITranslatedChoiceService,
    choiceNavigationService: IChoiceNavigationService
) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    withHeaderNames(HttpHeaders.ContentLanguage) {
                        contentType(ContentType.Application.Json) {
                            post("/choices") {
                                val choiceForCreation = call.receive<TranslatedChoiceCmdDto>()
                                call.request.headers[HttpHeaders.ContentLanguage]?.let { codes ->
                                    translatedChoiceService.createTranslatedChoice(choiceForCreation, codes)
                                        ?.let { createdChoice ->
                                            call.createdIn(ChoiceLocation(createdChoice.id))
                                        }
                                }
                                println(choiceForCreation)
                            }
                        }
                        contentType(CustomContentType.Application.ChoiceWithResource) {
                            post("/choices") {
                                val choiceForCreation = call.receive<TranslatedChoiceWithResourcesCmdDto>()
                                translatedChoiceService.createTranslatedChoiceAndAttachResources(
                                    choiceForCreation,
                                    call.request.headers[HttpHeaders.ContentLanguage]!!
                                )?.let { createdChoice ->
                                    call.createdIn(ChoiceLocation(createdChoice.id))
                                }
                            }
                        }
                    }
                    exceptHeaderNames(HttpHeaders.ContentLanguage) {
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
                    }
                    withHeaderNames(HttpHeaders.AcceptLanguage) {
                        get<ChoiceLocation> {
                            call.request.headers[HttpHeaders.AcceptLanguage]?.let { codes ->
                                translatedChoiceService.getTranslatedChoiceDetailed(
                                    it.id,
                                    codes,
                                    includeEvent = true,
                                    includeResources = true, useDefaultLanguage = true
                                )?.let { choice ->
                                    call.respond(choice)
                                }
                            }
                        }
                        get<ChoicesLocation> { location ->
                            translatedChoiceService.getTranslatedChoicesPage(
                                location,
                                call.request.headers[HttpHeaders.AcceptLanguage]!!,
                                includeEvent = false,
                                includeResources = false
                            ).let { choices ->
                                call.respond(PageMetadata(choices, call.request.uri))
                            }

                        }
                    }
                    exceptHeaderNames(HttpHeaders.AcceptLanguage) {

                        get<ChoiceLocation> {
                            val choice = choiceService.getOne(it.id)
                            choice?.let {
                                call.respond(choice)
                            }
                        }
                        contentType(ContentType.Application.Json) {
                            get<ChoicesLocation> { location ->
                                choiceService.getPage(location).let { choices ->
                                    call.respond(PageMetadata(choices, call.request.uri))
                                }
                            }
                        }
                        contentType(CustomContentType.Application.ChoiceNavigation) {
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
}