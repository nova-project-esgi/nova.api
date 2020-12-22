package com.esgi.nova.core_api.events.events

import com.esgi.nova.core_api.events.commands.EventIdentifier
import java.io.Serializable

data class CreatedEventEvent(val eventId: EventIdentifier, val isDaily: Boolean, val isActive: Boolean) : Serializable
