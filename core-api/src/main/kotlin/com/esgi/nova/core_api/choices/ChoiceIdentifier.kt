package com.esgi.nova.core_api.choices

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

data class ChoiceIdentifier(override val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) :
    Identifier(identifier) {
    override fun toString(): String {
        return "ChoiceIdentifier(identifier='$identifier')"
    }
}