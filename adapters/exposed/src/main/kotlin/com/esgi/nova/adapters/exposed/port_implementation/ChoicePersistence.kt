package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ChoiceMapper
import com.esgi.nova.adapters.exposed.repositories.ChoiceRepository
import com.esgi.nova.ports.provided.ITotalIterable
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.choice.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.ChoiceDto
import com.esgi.nova.ports.required.IChoicePersistence
import com.google.inject.Inject

class ChoicePersistence @Inject constructor(
    private val choiceRepository: ChoiceRepository,
    private val choiceMapper: ChoiceMapper,
    private val dbContext: DatabaseContext
) : IChoicePersistence {
    override fun getAll(query: Query): List<ChoiceDto> = dbContext.connectAndExec { choiceMapper.toDtos(choiceRepository.getAll(query).toList()) }

    override fun create(element: ChoiceCmdDto): ChoiceDto? {
        TODO("Not yet implemented")
    }

    override fun getAllTotal(query: Query): ITotalIterable<ChoiceDto> {
        TODO("Not yet implemented")
    }
}