package com.esgi.nova.ports.provided.dtos

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class ChoiceDto(
    var title: String,
    var description: String,
    var resources: Iterable<ResourceDto>,
    var event: EventDto
)
