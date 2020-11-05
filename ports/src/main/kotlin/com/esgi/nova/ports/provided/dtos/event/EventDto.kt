package com.esgi.nova.ports.provided.dtos.event

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import java.util.*


open class EventDto(
    override var id: UUID,
    override var isDaily: Boolean,
    override var isActive: Boolean,
    var games: List<GameDto>,
    var choices: List<ChoiceDto>
) : IEvent, IId<UUID>
