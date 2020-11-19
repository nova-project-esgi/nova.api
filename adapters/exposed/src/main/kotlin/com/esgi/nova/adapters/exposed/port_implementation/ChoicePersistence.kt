package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ChoiceMapper
import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
import com.esgi.nova.adapters.exposed.repositories.choices.ChoiceRepository
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.required.IChoicePersistence
import com.google.inject.Inject
import java.util.*

class ChoicePersistence @Inject constructor(
        override val repository: ChoiceRepository,
        mapper: ChoiceMapper,
        dbContext: DatabaseContext
) : BasePersistence<UUID, ChoiceCmdDto, ChoiceEntity, ChoiceDto>(repository, mapper, dbContext), IChoicePersistence {

    override fun getAllByEventId(eventId: UUID) =
        dbContext.connectAndExec { mapper.toDtos(repository.getAllByEventId(eventId).toList()) }

}