package com.esgi.nova.core_api.choices.events

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.resources.ResourceIdentifier
import java.io.Serializable

data class UpdatedChoiceResourceEvent(
    val choiceResourceId: ResourceIdentifier,
    val choiceId: ChoiceIdentifier,
    val changeValue: Int
) : Serializable