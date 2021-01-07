package com.esgi.nova.core_api.games

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

data class GameIdentifier(override val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) :
    Identifier(identifier) {
    override fun toString(): String {
        return "GameIdentifier(identifier='$identifier')"
    }
}