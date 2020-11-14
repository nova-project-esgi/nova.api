package com.esgi.nova.adapters.web.endpoints.choice_resources

import com.esgi.nova.adapters.web.domain.entities.ChoiceResourceCmd
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IChoiceResourceService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class ChoiceResourcesRoute @Inject constructor(
    application: Application,
    choiceResourceService: IChoiceResourceService
) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    get<ChoiceResourcesLocation> { location ->
                        val choiceResource =
                            choiceResourceService.getOne(ChoiceResourcesKey(location.cId, location.rId))
                        choiceResource?.let {
                            call.respond(choiceResource)
                        }
                    }
                    post<ChoiceResourcesLocation> { location ->
                        val choiceResourceCmd = call.receive<ChoiceResourceCmd>()
                        val choiceResourceForCreation =
                            ChoiceResourceCmdDto(location.cId, location.rId, choiceResourceCmd.changeValue)
                        val createdChoiceResource = choiceResourceService.create(choiceResourceForCreation)
                        createdChoiceResource?.let {
                            call.createdIn(location)
                        }
                    }

                }
            }
        }
    }
}