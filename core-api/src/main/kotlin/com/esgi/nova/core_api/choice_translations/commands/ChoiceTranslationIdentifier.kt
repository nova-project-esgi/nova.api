package com.esgi.nova.core_api.choice_translations.commands

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory
import java.io.Serializable

class ChoiceTranslationIdentifier(override val identifier: String = IdentifierFactory.getInstance().generateIdentifier()): Identifier(){
    override fun toString(): String {
        return "ChoiceTranslationIdentifier(identifier='$identifier')"
    }
}