package com.esgi.nova.core_api.events.commands

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

class EventIdentifier(identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Identifier(identifier) {
    override fun toString(): String {
        return "EventIdentifier(identifier='$identifier')"
    }
}