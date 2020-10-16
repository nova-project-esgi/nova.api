package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.mappers.ChoiceMapper
import com.esgi.nova.adapters.exposed.repositories.ChoiceRepository
import com.esgi.nova.ports.provided.dtos.ChoiceDto
import com.esgi.nova.ports.required.IChoicePersistence
import com.google.inject.Inject

class ChoicePersistence @Inject constructor(
    private val choiceRepository: ChoiceRepository,
    private val choiceMapper: ChoiceMapper
) : IChoicePersistence {
    override fun getAll(): List<ChoiceDto> = choiceRepository.getAll().map { choiceMapper.toDto(it) }
}