package com.esgi.nova.adapters.exposed.repositories.choices_resources

import com.esgi.nova.adapters.exposed.domain.IGetAllIterableById
import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.adapters.exposed.tables.ChoiceResource
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ChoiceResourceByResourceRepository: IGetAllIterableById<UUID, ChoiceResourceEntity> {
    override fun getAllById(id: UUID): SizedIterable<ChoiceResourceEntity> =
            transaction { ChoiceResourceEntity.find { ChoiceResource.resource eq id } }
}