package com.esgi.nova.core_api.events

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

data class EventIdentifier(override val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) :
    Identifier(identifier) {
    override fun toString(): String {
        return "EventIdentifier(identifier='$identifier')"
    }
}