package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.adapters.exposed.tables.ChoiceResource
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import com.google.inject.Inject
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ChoiceResourceRepository @Inject constructor(private val dbContext: DatabaseContext) {
    fun getAll(): List<ChoiceResourceEntity> = transaction { ChoiceResourceEntity.all().toList() }
    fun create(element: ChoiceResourceCmdDto): ChoiceResourceEntity = transaction {
        ChoiceResourceEntity.new {
            changeValue = element.changeValue
            choice = ChoiceEntity[element.choiceId]
            resource = ResourceEntity[element.resourceId]
        }
    }

    fun getOne(id: ChoiceResourcesKey): ChoiceResourceEntity? =
        ChoiceResourceEntity.find { (ChoiceResource.resource eq id.resourceId) and (ChoiceResource.choice eq id.choiceId) }
            .singleOrNull()

    fun getAllByChoiceId(choiceId: UUID) = transaction { ChoiceResourceEntity.find { ChoiceResource.choice eq choiceId } }
    fun getAllByResourceId(resourceId: UUID) = transaction { ChoiceResourceEntity.find { ChoiceResource.resource eq resourceId } }
}