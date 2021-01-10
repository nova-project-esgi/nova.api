package com.esgi.nova.games.write

import com.esgi.nova.core_api.games.events.UpdatedGameResourceEvent
import com.esgi.nova.core_api.resources.ResourceIdentifier
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.EntityId

class GameResourceEntity() {

    @EntityId
    lateinit var id: ResourceIdentifier

    var total: Int = 0

    constructor(id: ResourceIdentifier, total: Int) : this() {
        this.id = id
        this.total = total
    }

    @EventSourcingHandler
    fun on(event: UpdatedGameResourceEvent) {
        total = event.total
    }

}