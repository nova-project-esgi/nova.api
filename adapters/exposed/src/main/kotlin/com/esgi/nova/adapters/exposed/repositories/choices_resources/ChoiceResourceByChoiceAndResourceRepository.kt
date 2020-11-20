package com.esgi.nova.adapters.exposed.repositories.choices_resources

import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.adapters.exposed.tables.ChoiceResource
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import org.jetbrains.exposed.sql.and

class ChoiceResourceByChoiceAndResourceRepository: BaseChoiceResourceRepository<ChoiceResourcesKey>() {
    override fun getOne(id: ChoiceResourcesKey): ChoiceResourceEntity? =
            ChoiceResourceEntity.find { (ChoiceResource.resource eq id.resourceId) and (ChoiceResource.choice eq id.choiceId) }
                    .singleOrNull()
}