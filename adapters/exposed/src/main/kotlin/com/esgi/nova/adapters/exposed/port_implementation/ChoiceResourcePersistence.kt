package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ChoiceResourceMapper
import com.esgi.nova.adapters.exposed.repositories.ChoiceResourceRepository
import com.esgi.nova.ports.provided.ITotalIterable
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject

class ChoiceResourcePersistence @Inject constructor(
    private val choiceResourceRepository: ChoiceResourceRepository,
    private val choiceResourceMapper: ChoiceResourceMapper,
    private val dbContext: DatabaseContext
) : IChoiceResourcePersistence {
    override fun getAll(query: Query): List<ChoiceResourceDto> = dbContext.connectAndExec {
        choiceResourceMapper.toDtos(choiceResourceRepository.getAll(query))
    }

    override fun create(element: ChoiceResourceDto): ChoiceResourceDto? {
        TODO("Not yet implemented")
    }

    override fun getAllTotal(query: Query): ITotalIterable<ChoiceResourceDto> {
        TODO("Not yet implemented")
    }
}