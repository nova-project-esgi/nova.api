package com.esgi.nova.files.core.api

import com.esgi.nova.core_api.Identifier
import org.axonframework.common.IdentifierFactory

data class FileIdentifier(override val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) :
        Identifier(identifier) {

    override fun toString(): String {
        return "FileIdentifier(identifier='$identifier')"
    }

}