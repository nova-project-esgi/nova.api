package com.esgi.nova.ports.provided.dtos

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class ResourceDto(var name: String, var choices: Iterable<ChoiceDto>)
