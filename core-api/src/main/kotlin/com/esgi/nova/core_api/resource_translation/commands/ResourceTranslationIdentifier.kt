package com.esgi.nova.core_api.resource_translation.commands

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory
import java.io.Serializable

class ResourceTranslationIdentifier(
    val languageId: String = IdentifierFactory.getInstance().generateIdentifier(),
    val resourceId: String = IdentifierFactory.getInstance().generateIdentifier()
) : Serializable {

    override fun toString(): String {
        return "ResourceTranslationIdentifier(languageId='$languageId', resourceId='$resourceId')"
    }
}