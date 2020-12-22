package com.esgi.nova.core_api.resource_translation.commands

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

class ResourceTranslationIdentifier(
    override val identifier: String = IdentifierFactory.getInstance().generateIdentifier()
) : Identifier(identifier) {
    override fun toString(): String {
        return "ResourceTranslationIdentifier(identifier='$identifier')"
    }
}