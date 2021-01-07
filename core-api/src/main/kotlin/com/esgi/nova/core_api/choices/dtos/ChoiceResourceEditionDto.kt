package com.esgi.nova.core_api.choices.dtos

import com.esgi.nova.core_api.resources.ResourceIdentifier

data class ChoiceResourceEditionDto(
    val choiceResourceId: ResourceIdentifier,
    val changeValue: Int
)