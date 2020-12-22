package com.esgi.nova.core_api.event_translations.events

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.events.commands.EventIdentifier
import java.io.Serializable


data class AddedChoiceEvent(val eventId: EventIdentifier, val choiceId: ChoiceIdentifier): Serializable