package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.adapters.exposed.tables.ChoiceResource
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ChoiceResourceRepository :
    IRepository<ChoiceResourcesKey, ChoiceResourceCmdDto, ChoiceResourceEntity > {
    override fun getAll(): SizedIterable<ChoiceResourceEntity> = transaction { ChoiceResourceEntity.all() }
    override fun create(element: ChoiceResourceCmdDto): ChoiceResourceEntity = transaction {
        ChoiceResourceEntity.new {
            changeValue = element.changeValue
            ChoiceEntity.findById(element.choiceId)?.let { choiceEntity -> choice = choiceEntity }
            ResourceEntity.findById(element.resourceId)?.let { resourceEntity -> resource = resourceEntity }
            resource = ResourceEntity[element.resourceId]
        }
    }

    override fun getOne(id: ChoiceResourcesKey): ChoiceResourceEntity? =
        ChoiceResourceEntity.find { (ChoiceResource.resource eq id.resourceId) and (ChoiceResource.choice eq id.choiceId) }
            .singleOrNull()

    fun getAllByChoiceId(choiceId: UUID) =
        transaction { ChoiceResourceEntity.find { ChoiceResource.choice eq choiceId } }

    fun getAllByResourceId(resourceId: UUID) =
        transaction { ChoiceResourceEntity.find { ChoiceResource.resource eq resourceId } }

    fun updateOne(id: ChoiceResourcesKey, element: ChoiceResourceCmdDto) = transaction {
        getOne(id)?.also { choiceResource ->
            ResourceEntity.findById(element.resourceId)
                ?.let { resourceEntity -> choiceResource.resource = resourceEntity }
            ChoiceEntity.findById(element.choiceId)?.let { choiceEntity -> choiceResource.choice = choiceEntity }
            choiceResource.changeValue = element.changeValue
        }
    }

    override fun updateOne(element: ChoiceResourceCmdDto, id: ChoiceResourcesKey): ChoiceResourceEntity? {
        TODO("Not yet implemented")
    }

    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<ChoiceResourceEntity> {
        TODO("Not yet implemented")
    }
}