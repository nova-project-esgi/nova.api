package com.esgi.nova.adapters.web.endpoints.resource_translations

import com.esgi.nova.adapters.web.domain.ResourceTranslationCmd
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IResourceTranslationCodesService
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
    resourceTranslationCodesService: IResourceTranslationCodesService
) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    post<ResourceTranslationsLocation> { location ->
                        val resourceForCreation = call.receive<ResourceTranslationCmd>()
                        resourceTranslationCodesService.create(
                            ResourceTranslationCmdDto(
                                resourceForCreation.name,
                                location.rId,
                                location.codes
                            )
                        )?.let {
                            call.createdIn(location)
                        }
                    }
                    get<ResourceTranslationsLocation> { location ->
                        resourceTranslationCodesService.getOne(ResourceTranslationKey(location.rId, location.codes))
                            ?.let { resourceTranslationDto ->
                                call.respond(resourceTranslationDto)
                            }
                    }
                }
            }
        }
    }
}