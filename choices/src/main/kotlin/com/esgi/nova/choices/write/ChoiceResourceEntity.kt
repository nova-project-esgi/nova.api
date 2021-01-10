package com.esgi.nova.choices.write

import com.esgi.nova.core_api.choices.events.UpdatedChoiceResourceEvent
import com.esgi.nova.core_api.resources.ResourceIdentifier
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.EntityId

class ChoiceResourceEntity() {

    @EntityId
    lateinit var id: ResourceIdentifier

    var changeValue: Int = 0

    constructor(id: ResourceIdentifier, changeValue: Int) : this() {
        this.id = id
        this.changeValue = changeValue
    }

    @EventSourcingHandler
    fun on(event: UpdatedChoiceResourceEvent) {
        changeValue = event.changeValue
    }
}
