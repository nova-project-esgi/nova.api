package com.esgi.nova.core_api.events.events

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.events.EventIdentifier
import java.io.Serializable


data class AddedChoiceEvent(val eventId: EventIdentifier, val choiceId: ChoiceIdentifier) : Serializable