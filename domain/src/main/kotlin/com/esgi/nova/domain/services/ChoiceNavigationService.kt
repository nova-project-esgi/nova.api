package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceNavigationDto
import com.esgi.nova.ports.provided.services.IChoiceNavigationService
import com.esgi.nova.ports.required.IChoicePersistence
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject
import java.util.*

class ChoiceNavigationService @Inject constructor(
    override val persistence: IChoicePersistence,
    private val choiceResourcePersistence: IChoiceResourcePersistence
) : BaseIntermediateMappingService<UUID, ChoiceNavigationDto, ChoiceCmdDto, ChoiceDto>(persistence), IChoiceNavigationService {
    override fun toFinalOutput(output: ChoiceDto): ChoiceNavigationDto {
        val choiceResources = choiceResourcePersistence.getAllByChoiceId(output.id)
        return ChoiceNavigationDto(output.id, choiceResources.map { el-> el.resource.id },output.event.id);
    }
}