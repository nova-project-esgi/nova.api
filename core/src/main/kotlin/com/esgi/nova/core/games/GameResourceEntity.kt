package com.esgi.nova.core.games

import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.games.events.UpdatedGameResourceEvent
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.EntityId
import org.springframework.core.io.Resource
import java.time.LocalDateTime

class GameResourceEntity() {

    @EntityId
    lateinit var id: ResourceIdentifier

    var total: Int = 0
    constructor(id: ResourceIdentifier, total: Int) : this() {
        this.id = id
        this.total = total
    }

    @EventSourcingHandler
    fun on(event: UpdatedGameResourceEvent){
        total = event.total
    }

}