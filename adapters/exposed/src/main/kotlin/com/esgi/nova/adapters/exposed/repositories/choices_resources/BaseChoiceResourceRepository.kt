package com.esgi.nova.adapters.exposed.repositories.choices_resources

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction

abstract class BaseChoiceResourceRepository<ID>:
        IRepository<ID, ChoiceResourceCmdDto, ChoiceResourceEntity>{
    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<ChoiceResourceEntity> = transaction {
        val elements = ChoiceResourceEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    override fun create(element: ChoiceResourceCmdDto): ChoiceResourceEntity? = transaction {
        ChoiceResourceEntity.new {
            changeValue = element.changeValue
            ChoiceEntity.findById(element.choiceId)?.let { choiceEntity -> choice = choiceEntity }
            ResourceEntity.findById(element.resourceId)?.let { resourceEntity -> resource = resourceEntity }
            resource = ResourceEntity[element.resourceId]
        }
    }

    override fun getAll(): SizedIterable<ChoiceResourceEntity> = transaction { ChoiceResourceEntity.all() }

    override fun updateOne(
            element: ChoiceResourceCmdDto,
            id: ID
    ): ChoiceResourceEntity? = transaction {
        getOne(id)?.also { choiceResource ->
            ResourceEntity.findById(element.resourceId)
                    ?.let { resourceEntity -> choiceResource.resource = resourceEntity }
            ChoiceEntity.findById(element.choiceId)?.let { choiceEntity -> choiceResource.choice = choiceEntity }
            choiceResource.changeValue = element.changeValue
        }
    }
}