package com.esgi.nova.core_api.choices.commands

import com.esgi.nova.core_api.resources.commands.ResourceIdentifier

data class ChoiceResourceEditionDto(
    val choiceResourceId: ResourceIdentifier,
    val changeValue:Int
)