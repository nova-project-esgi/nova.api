package com.esgi.nova.core_api.user

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

class UserIdentifier(identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Identifier(identifier) {

    override fun toString(): String {
        return "UserIdentifier(identifier='$identifier')"
    }

}