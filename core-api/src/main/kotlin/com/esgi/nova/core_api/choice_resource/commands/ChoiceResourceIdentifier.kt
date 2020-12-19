package com.esgi.nova.core_api.choice_resource.commands

import org.axonframework.common.IdentifierFactory
import java.io.Serializable

data class ChoiceResourceIdentifier(
    val choideId: String = IdentifierFactory.getInstance().generateIdentifier(),
    val resourceId: String = IdentifierFactory.getInstance().generateIdentifier()
) : Serializable {
    override fun toString(): String {
        return "ChoiceResourceIdentifier(choideId='$choideId', resourceId='$resourceId')"
    }
}