package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.ChoiceMapper
import com.esgi.nova.adapters.exposed.repositories.ChoiceRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.required.IChoicePersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class ChoicePersistence @Inject constructor(
    private val choiceRepository: ChoiceRepository,
    private val choiceMapper: ChoiceMapper,
    private val dbContext: DatabaseContext
) : IChoicePersistence {
    override fun getAll(): Collection<ChoiceDto> =
        dbContext.connectAndExec { choiceMapper.toDtos(choiceRepository.getAll().toList()) }

    override fun create(element: ChoiceCmdDto): ChoiceDto? =
        dbContext.connectAndExec { choiceMapper.toDto(choiceRepository.create(element)) }

    override fun getAllTotal(pagination: IPagination): ITotalCollection<ChoiceDto> = dbContext.connectAndExec {
        val elements = choiceRepository.getAllTotal(DatabasePagination(pagination))
        TotalCollection(elements.total, choiceMapper.toDtos(elements))
    }

    override fun getAllByEventId(eventId: UUID) =
        dbContext.connectAndExec { choiceMapper.toDtos(choiceRepository.getAllByEventId(eventId).toList()) }

    override fun getOne(id: UUID): ChoiceDto? =
        dbContext.connectAndExec { choiceRepository.getOne(id)?.let { choice -> choiceMapper.toDto(choice) } }

    override fun updateOne(element: ChoiceCmdDto, id: UUID): ChoiceDto? = dbContext.connectAndExec {
        choiceRepository.updateOne(id, element)?.let { choice -> choiceMapper.toDto(choice) }
    }


}