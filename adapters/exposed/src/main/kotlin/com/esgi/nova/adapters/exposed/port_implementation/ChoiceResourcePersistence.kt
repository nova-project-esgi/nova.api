package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.mappers.ChoiceResourceMapper
import com.esgi.nova.adapters.exposed.repositories.ChoiceResourceRepository
import com.esgi.nova.ports.provided.dtos.ChoiceResourceDto
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject

class ChoiceResourcePersistence @Inject constructor(
    private val choiceResourceRepository: ChoiceResourceRepository,
    private val choiceResourceMapper: ChoiceResourceMapper
) : IChoiceResourcePersistence {
    override fun getAll(): List<ChoiceResourceDto> =
        choiceResourceRepository.getAll().map { choiceResourceMapper.toDto(it) }
}