package com.esgi.nova.ports.provided.dtos

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class EventDto(
    var title: String,
    var description: String,
    var isDaily: Boolean,
    var isActive: Boolean,
    var games: Iterable<GameDto>
)
