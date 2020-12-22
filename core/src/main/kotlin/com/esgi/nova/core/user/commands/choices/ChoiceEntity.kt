package com.esgi.nova.core.user.commands.choices

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import org.axonframework.modelling.command.EntityId


class ChoiceEntity() {
    @EntityId
    private lateinit var id: ChoiceIdentifier

    constructor(id: ChoiceIdentifier) : this() {
        this.id = id
    }
}
