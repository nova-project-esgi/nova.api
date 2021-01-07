package com.esgi.nova.core_api.languages

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

class LanguageIdentifier(identifier: String = IdentifierFactory.getInstance().generateIdentifier()) :
    Identifier(identifier) {
    override fun toString(): String {
        return "LanguageIdentifier(identifier='$identifier')"
    }

}