package com.esgi.nova.core.choices

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import org.axonframework.modelling.command.EntityId


class ChoiceEntity() {
    @EntityId
    lateinit var id: ChoiceIdentifier

    constructor(id: ChoiceIdentifier) : this() {
        this.id = id
    }
}
