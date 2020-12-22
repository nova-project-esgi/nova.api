package com.esgi.nova.core_api.event_translations.commands

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

class EventTranslationIdentifier(
    override val identifier: String = IdentifierFactory.getInstance().generateIdentifier()
) : Identifier(identifier) {
    override fun toString(): String {
        return "EventTranslationIdentifier(identifier='$identifier')"
    }
}
