package com.esgi.nova.core_api.choices.events

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.events.EventIdentifier
import java.io.Serializable

data class CreatedChoiceEvent(
    val choiceId: ChoiceIdentifier,
    val eventId: EventIdentifier
) : Serializable

