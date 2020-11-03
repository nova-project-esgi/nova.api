package com.esgi.nova.adapters.web.endpoints.resources

import com.esgi.nova.adapters.web.domain.pagination.PageMetadata
import com.esgi.nova.ports.provided.dtos.resource.translated_resource_detailed.TranslatedResourceDetailedDto
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.exceptHeaderNames
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.adapters.web.extensions.withHeaderNames
import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceCmdDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IResourceService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class ResourcesRoute @Inject constructor(application: Application, resourceService: IResourceService) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    withHeaderNames(HttpHeaders.ContentLanguage) {
                        post("/resources") {
                            val resource = call.receive<TranslatedResourceCmdDto>()
                            resourceService.createTranslatedResource(
                                resource,
                                call.request.headers[HttpHeaders.ContentLanguage]!!
                            )?.let { createdResource ->
                                call.createdIn(ResourceLocation(createdResource.id))
                            }

                        }
                    }
                    exceptHeaderNames(HttpHeaders.ContentLanguage) {
                        post("/resources") {
                            val resourceCmd = call.receive<ResourceCmdDto>()
                            try {
                                val createdResource = resourceService.create(resourceCmd)
                                createdResource?.let {
                                    call.createdIn(ResourceLocation(id = createdResource.id))
                                }
                            } catch (e: Exception) {
                                println(e)
                            }
                        }
                    }
                }
                withHeaderNames(HttpHeaders.AcceptLanguage) {
                    get<ResourceLocation> { location ->
                        resourceService.getTranslatedResourceDetailedDto(
                            id = location.id,
                            codes = call.request.headers[HttpHeaders.AcceptLanguage]!!, includeChoices = true,
                            useDefaultLanguage = true
                        )?.let { translatedResource ->
                            call.respond(translatedResource)
                        }
                    }
                    get<ResourcesLocation> { location ->
                        resourceService.getTranslatedResourcesPage(
                            location,
                            call.request.headers[HttpHeaders.AcceptLanguage]!!, includeChoices = false
                        ).let { translatedResources ->
                            call.respond(PageMetadata(translatedResources, call.request.uri))
                        }
                    }

                }
                exceptHeaderNames(HttpHeaders.AcceptLanguage) {
                    get<ResourceLocation> {
                        val resource = resourceService.getOne(it.id)
                        resource?.let {
                            call.respond(resource)
                        }
                    }
                    get<ResourcesLocation> { location ->
                        val resources = resourceService.getPage(location)
                        call.respond(PageMetadata(resources, call.request.uri))
                    }
                }
            }
        }
    }
}