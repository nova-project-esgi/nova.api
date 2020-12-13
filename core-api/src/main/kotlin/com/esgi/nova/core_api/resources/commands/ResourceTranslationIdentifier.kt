package com.esgi.nova.core_api.resources.commands

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

class ResourceTranslationIdentifier(identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Identifier(identifier) {
    override fun toString(): String {
        return "ResourceTranslationIdentifier()"
    }
}