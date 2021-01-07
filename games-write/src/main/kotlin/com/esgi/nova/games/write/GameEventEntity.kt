package com.esgi.nova.games.write

import com.esgi.nova.core_api.events.EventIdentifier
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
