package com.esgi.nova.adapters.web.endpoints.translated_resources

import com.esgi.nova.adapters.web.domain.CustomContentType
import com.esgi.nova.adapters.web.domain.pagination.PageMetadata
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceCmdDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.resources.ITranslatedResourceService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class TranslatedResourcesRoute @Inject constructor(application: Application, translatedResourceService: ITranslatedResourceService) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    contentType(CustomContentType.Application.TranslatedResource) {
                        post("/resources") {
                            val resource = call.receive<TranslatedResourceCmdDto>()
                            translatedResourceService.createTranslatedResource(
                                    resource
                            )?.let { createdResource ->
                                call.createdIn(TranslatedResourceLocation(createdResource.id, resource.language))
                            }

                        }
                    }

                }
                accept(CustomContentType.Application.TranslatedResource) {
                    get<TranslatedResourceLocation> { location ->
                        translatedResourceService.getTranslatedResourceDetailedDto(
                                id = location.id,
                                codes = location.language, includeChoices = true,
                                useDefaultLanguage = true
                        )?.let { translatedResource ->
                            call.respond(translatedResource)
                        }
                    }
                    get<TranslatedResourcesLocation> { location ->
                        translatedResourceService.getTranslatedResourcesPage(
                                location,
                                location.language, includeChoices = false
                        ).let { translatedResources ->
                            call.respond(PageMetadata(translatedResources, call.request.uri))
                        }
                    }

                }

            }
        }
    }
}