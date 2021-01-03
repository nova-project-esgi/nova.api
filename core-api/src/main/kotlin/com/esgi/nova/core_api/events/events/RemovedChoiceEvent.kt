package com.esgi.nova.core_api.events.events

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.events.commands.EventIdentifier


data class RemovedChoiceEvent(val eventId: EventIdentifier, val choiceId: ChoiceIdentifier)