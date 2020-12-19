package com.esgi.nova.core_api.choice_translations.commands

import org.axonframework.common.IdentifierFactory
import java.io.Serializable

class ChoiceTranslationIdentifier(
    val choiceId: String = IdentifierFactory.getInstance().generateIdentifier(),
    val languageId: String = IdentifierFactory.getInstance().generateIdentifier()
): Serializable {
    override fun toString(): String {
        return "ChoiceTranslationIdentifier(choiceId='$choiceId', languageId='$languageId')"
    }
}