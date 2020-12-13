package com.esgi.nova.core_api

import org.axonframework.common.IdentifierFactory
import java.io.Serializable
import java.util.*

abstract class Identifier(val identifier: String = IdentifierFactory.getInstance().generateIdentifier()) : Serializable {
    fun toUUID(): UUID = UUID.fromString(identifier)
}