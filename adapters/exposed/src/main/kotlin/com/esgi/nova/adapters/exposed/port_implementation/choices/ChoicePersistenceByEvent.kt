package com.esgi.nova.adapters.exposed.port_implementation.choices

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ChoiceMapper
import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.IPersistenceGetAllById
import com.esgi.nova.adapters.exposed.repositories.choices.ChoiceByEventRepository
import com.esgi.nova.adapters.exposed.repositories.choices.ChoiceRepository
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.google.inject.Inject
import java.util.*

class ChoicePersistenceByEvent @Inject constructor(repository: ChoiceRepository,
                                                   val choiceByEventRepository: ChoiceByEventRepository,
                                                   mapper: ChoiceMapper,
                                                   dbContext: DatabaseContext) : ChoicePersistence(repository,
        mapper,
        dbContext), IPersistenceGetAllById<UUID, ChoiceDto, ChoiceEntity> {
    override fun getAllById(id: UUID): Collection<ChoiceDto> = getAllById(choiceByEventRepository, id)
}