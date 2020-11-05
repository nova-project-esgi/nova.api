package com.esgi.nova.domain.services

import com.esgi.nova.domain.extensions.toPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import com.esgi.nova.ports.provided.services.IChoiceResourceService
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject
import java.util.*

class ChoiceResourceService @Inject constructor(private val choiceResourcePersistence: IChoiceResourcePersistence) :
    IChoiceResourceService {
    override fun getAll() = choiceResourcePersistence.getAll()
    override fun getPage(pagination: IPagination): IPage<ChoiceResourceDto> =
        choiceResourcePersistence.getAllTotal(pagination).toPage(pagination)


    override fun create(element: ChoiceResourceCmdDto): ChoiceResourceDto? = choiceResourcePersistence.create(element)
    override fun getOne(id: ChoiceResourcesKey): ChoiceResourceDto? = choiceResourcePersistence.getOne(id)
    override fun updateOne(element: ChoiceResourceCmdDto, id: ChoiceResourcesKey): ChoiceResourceDto? = choiceResourcePersistence.updateOne(
        element,
        id
    )

    override fun getAllByResourceId(resourceId: UUID) = choiceResourcePersistence.getAllByResourceId(resourceId)
    override fun getAllByChoiceId(choiceId: UUID): Collection<ChoiceResourceDto> =
        choiceResourcePersistence.getAllByChoiceId(choiceId)

}