package com.esgi.nova.core_api.events.events

import com.esgi.nova.core_api.events.EventIdentifier
import java.io.Serializable

data class DeletedEventEvent(val eventId: EventIdentifier) : Serializable

