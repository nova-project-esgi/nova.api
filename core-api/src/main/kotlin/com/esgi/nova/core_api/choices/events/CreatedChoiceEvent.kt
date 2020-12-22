package com.esgi.nova.core_api.choices.events

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import java.io.Serializable

data class CreatedChoiceEvent(
    val choiceId: ChoiceIdentifier,
    val eventId: EventIdentifier
) : Serializable

