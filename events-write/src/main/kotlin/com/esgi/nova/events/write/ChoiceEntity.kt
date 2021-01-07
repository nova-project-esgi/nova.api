package com.esgi.nova.events.write

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import org.axonframework.modelling.command.EntityId


class ChoiceEntity() {
    @EntityId
    lateinit var id: ChoiceIdentifier

    constructor(id: ChoiceIdentifier) : this() {
        this.id = id
    }
}
