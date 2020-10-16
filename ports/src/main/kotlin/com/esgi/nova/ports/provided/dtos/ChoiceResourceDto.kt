package com.esgi.nova.ports.provided.dtos

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class ChoiceResourceDto(var choice: ChoiceDto, var resouce: ResourceDto, var changeValue: Int)
