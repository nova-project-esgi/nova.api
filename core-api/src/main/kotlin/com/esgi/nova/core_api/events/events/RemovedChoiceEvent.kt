package com.esgi.nova.core_api.events.events

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.events.EventIdentifier


data class RemovedChoiceEvent(val eventId: EventIdentifier, val choiceId: ChoiceIdentifier)