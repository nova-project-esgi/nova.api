package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import com.esgi.nova.ports.provided.services.IChoiceResourceService
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject
import java.awt.Choice
import java.util.*

class ChoiceResourceService @Inject constructor(private val choiceResourcePersistence: IChoiceResourcePersistence) :
    IChoiceResourceService {
    override fun getAll() = choiceResourcePersistence.getAll()
    override fun getPage(pagination: IPagination): IPage<Choice> {
        TODO("Not yet implemented")
    }

    override fun create(element: ChoiceResourceCmdDto): ChoiceResourceDto? = choiceResourcePersistence.create(element)
    override fun getOne(id: ChoiceResourcesKey): ChoiceResourceDto? = choiceResourcePersistence.getOne(id)
    override fun getAllByResourceId(resourceId: UUID) = choiceResourcePersistence.getAllByResourceId(resourceId)
    override fun getAllByChoiceId(choiceId: UUID): Collection<ChoiceResourceDto> =
        choiceResourcePersistence.getAllByChoiceId(choiceId)

}