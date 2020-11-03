package com.esgi.nova.adapters.web.endpoints.resource_translations

import com.esgi.nova.adapters.web.domain.ResourceTranslationCmd
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationLanguageCodesCmdDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IResourceTranslationService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class ResourceTranslationsRoute @Inject constructor(
    application: Application,
    resourceTranslationService: IResourceTranslationService
) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    post<ResourceTranslationsLocation> { location ->
                        val resourceForCreation = call.receive<ResourceTranslationCmd>()
                        resourceTranslationService.createWithCodes(
                            ResourceTranslationLanguageCodesCmdDto(
                                resourceForCreation.name,
                                location.rId,
                                location.codes
                            )
                        )?.let {
                            call.createdIn(location)
                        }
                    }
                    get<ResourceTranslationsLocation> { location ->
                        resourceTranslationService.getOneWithCodes(location.rId, location.codes)
                            ?.let { resourceTranslationDto ->
                                call.respond(resourceTranslationDto)
                            }
                    }
                }
            }
        }
    }
}