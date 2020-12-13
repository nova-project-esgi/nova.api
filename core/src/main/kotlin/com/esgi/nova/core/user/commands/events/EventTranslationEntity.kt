package com.esgi.nova.core.user.commands.events

import com.esgi.nova.core_api.events.commands.EventTranslationIdentifier
import org.axonframework.modelling.command.EntityId

class EventTranslationEntity() {

    @EntityId
    lateinit var id: EventTranslationIdentifier

    lateinit var title: String
    lateinit var description: String

    constructor(id: EventTranslationIdentifier, title: String, description: String) : this() {
        this.id = id
        this.title = title
        this.description = description
    }

}
