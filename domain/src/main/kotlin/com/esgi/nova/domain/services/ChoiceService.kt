package com.esgi.nova.domain.services

import com.esgi.nova.domain.extensions.toPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceWithResourcesCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.services.*
import com.esgi.nova.ports.required.IChoicePersistence
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject
import java.util.*

class ChoiceService @Inject constructor(
    private val choicePersistence: IChoicePersistence,
    private val choiceResourcePersistence: IChoiceResourcePersistence,
) : IChoiceService {

    override fun createChoiceAndAttachResources(element: ChoiceWithResourcesCmdDto): ChoiceDto? {
        val choice = choicePersistence.create(element)
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


    override fun getAll() = choicePersistence.getAll()
    override fun getOne(id: UUID): ChoiceDto? = choicePersistence.getOne(id)

    override fun create(element: ChoiceCmdDto): ChoiceDto? = choicePersistence.create(element)
    override fun getPage(pagination: IPagination): IPage<ChoiceDto> =
        choicePersistence.getAllTotal(pagination).toPage(pagination)

    override fun updateOne(element: ChoiceCmdDto, id: UUID): ChoiceDto? = choicePersistence.updateOne(element, id)
}