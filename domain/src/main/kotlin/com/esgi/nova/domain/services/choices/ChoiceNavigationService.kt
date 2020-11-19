package com.esgi.nova.domain.services.choices

import com.esgi.nova.domain.services.mapping_service.BaseMappingService
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceNavigationDto
import com.esgi.nova.ports.provided.services.choices.IChoiceNavigationService
import com.esgi.nova.ports.required.IChoicePersistence
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject
import java.util.*

class ChoiceNavigationService @Inject constructor(
    override val persistence: IChoicePersistence,
    private val choiceResourcePersistence: IChoiceResourcePersistence
) : BaseMappingService<UUID, ChoiceDto, ChoiceCmdDto, ChoiceNavigationDto>(persistence), IChoiceNavigationService {
    override fun map(obj: ChoiceDto): ChoiceNavigationDto {
        val choiceResources = choiceResourcePersistence.getAllByChoiceId(obj.id)
        return ChoiceNavigationDto(obj.id, choiceResources.map { el-> el.resource.id },obj.event.id);
    }
}