package com.esgi.nova.domain.services

import com.esgi.nova.domain.extensions.toPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceWithResourcesCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.provided.services.IChoiceService
import com.esgi.nova.ports.required.IChoicePersistence
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject
import java.util.*

class ChoiceService @Inject constructor(
    override val persistence: IChoicePersistence,
    private val choiceResourcePersistence: IChoiceResourcePersistence
) : BaseService<UUID, ChoiceCmdDto, ChoiceDto>(persistence), IChoiceService {

    override fun createChoiceAndAttachResources(element: ChoiceWithResourcesCmdDto): ChoiceDto? {
        val choice = persistence.create(element)
        choice?.let {
            element.resources.forEach { resourceChange ->
                val choiceResource = ChoiceResourceCmdDto(
                    choiceId = choice.id,
                    resourceId = resourceChange.resourceId,
                    changeValue = resourceChange.changeValue
                )
                choiceResourcePersistence.create(choiceResource)
            }
        }
        return choice
    }

}