package com.esgi.nova.core_api.difficulty.commands

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

data class DifficultyIdentifier(override val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Identifier(identifier) {
    override fun toString(): String {
        return "DifficultyIdentifier(identifier='$identifier')"
    }
}