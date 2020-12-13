package com.esgi.nova.core_api.events.commands

import org.axonframework.common.IdentifierFactory
import java.io.Serializable

class EventTranslationIdentifier(val languageId: String = IdentifierFactory.getInstance().generateIdentifier(), val eventId: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable {
    override fun toString(): String {
        return "EventTranslationIdentifier(languageId='$languageId', eventId='$eventId')"
    }
}
