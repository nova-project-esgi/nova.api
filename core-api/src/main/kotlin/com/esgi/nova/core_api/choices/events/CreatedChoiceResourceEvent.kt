package com.esgi.nova.core_api.choices.events

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import java.io.Serializable

data class CreatedChoiceResourceEvent(
    val choiceResourceId: ResourceIdentifier,
    val choiceId: ChoiceIdentifier,
    val changeValue: Int
) : Serializable

