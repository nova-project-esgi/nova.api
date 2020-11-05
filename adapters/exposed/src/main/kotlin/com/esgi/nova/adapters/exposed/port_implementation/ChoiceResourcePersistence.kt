package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ChoiceResourceMapper
import com.esgi.nova.adapters.exposed.repositories.ChoiceResourceRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class ChoiceResourcePersistence @Inject constructor(
    private val choiceResourceRepository: ChoiceResourceRepository,
    private val choiceResourceMapper: ChoiceResourceMapper,
    private val dbContext: DatabaseContext
) : IChoiceResourcePersistence {
    override fun getAll(): Collection<ChoiceResourceDto> = dbContext.connectAndExec {
        choiceResourceMapper.toDtos(choiceResourceRepository.getAll())
    }

    override fun create(element: ChoiceResourceCmdDto): ChoiceResourceDto? =
        dbContext.connectAndExec { choiceResourceMapper.toDto(choiceResourceRepository.create(element)) }

    override fun getAllTotal(pagination: IPagination): ITotalCollection<ChoiceResourceDto> {
        TODO("Not yet implemented")
    }

    override fun getAllByResourceId(resourceId: UUID) = dbContext.connectAndExec {
        choiceResourceMapper.toDtos(
            choiceResourceRepository.getAllByResourceId(resourceId).toList()
        )
    }

    override fun getAllByChoiceId(choiceId: UUID): Collection<ChoiceResourceDto> = dbContext.connectAndExec {
        choiceResourceMapper.toDtos(
            choiceResourceRepository.getAllByChoiceId(choiceId).toList()
        )
    }

    override fun getOne(id: ChoiceResourcesKey): ChoiceResourceDto? =
        dbContext.connectAndExec {
            choiceResourceRepository.getOne(id)?.let { choiceResource -> choiceResourceMapper.toDto(choiceResource) }
        }

    override fun updateOne(element: ChoiceResourceCmdDto, id: ChoiceResourcesKey): ChoiceResourceDto? = dbContext.connectAndExec {
        choiceResourceRepository.updateOne(id, element)?.let{choiceResource -> choiceResourceMapper.toDto(choiceResource)}
    }


}