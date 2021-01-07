package com.esgi.nova.core_api.resources

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

data class ResourceIdentifier(override val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) :
    Identifier(identifier) {
    override fun toString(): String {
        return "ResourceIdentifier(identifier='$identifier')"
    }
}