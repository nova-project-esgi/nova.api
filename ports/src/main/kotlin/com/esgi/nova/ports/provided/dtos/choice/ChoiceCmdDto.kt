package com.esgi.nova.ports.provided.dtos.choice

import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.github.pozo.KotlinBuilder
import java.util.*

@KotlinBuilder
data class ChoiceCmdDto(
    var title: String,
    var description: String,
    var resources: List<ResourceDto>,
    var event: EventDto
)
