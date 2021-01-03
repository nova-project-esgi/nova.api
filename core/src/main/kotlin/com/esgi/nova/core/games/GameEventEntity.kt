package com.esgi.nova.core.games

import com.esgi.nova.core_api.events.commands.EventIdentifier
import org.axonframework.modelling.command.EntityId
import java.time.LocalDateTime

class GameEventEntity() {

    @EntityId
    lateinit var id: EventIdentifier

    lateinit var linkTime: LocalDateTime;

    constructor(id: EventIdentifier, linkTime: LocalDateTime) : this() {
        this.id = id
        this.linkTime = linkTime
    }

}
