package com.esgi.nova.core_api.events.events

import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.event_translations.commands.EventTranslationIdentifier
import java.io.Serializable

data class UpdatedEventEvent(
    val eventId: EventIdentifier,
    val isDaily: Boolean,
    val isActive: Boolean,
    val translations: List<EventTranslationIdentifier>
): Serializable