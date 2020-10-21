package com.esgi.nova.ports.provided.dtos.event

import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.github.pozo.KotlinBuilder
import java.util.*

@KotlinBuilder
data class EventCmdDto(
    var title: String,
    var description: String,
    var isDaily: Boolean,
    var isActive: Boolean,
    var games: List<GameDto>
)
