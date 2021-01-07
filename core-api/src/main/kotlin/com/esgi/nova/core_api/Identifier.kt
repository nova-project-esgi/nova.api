package com.esgi.nova.core_api

import org.axonframework.common.IdentifierFactory
import java.io.Serializable
import java.util.*

abstract class Identifier(open val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) :
    Serializable {
    fun toUUID(): UUID = UUID.fromString(identifier)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Identifier) return false

        if (identifier != other.identifier) return false

        return true
    }

    override fun hashCode(): Int {
        return identifier.hashCode()
    }


}