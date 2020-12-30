package com.esgi.nova.core.choices

import com.esgi.nova.core_api.choice_resource.events.UpdatedChoiceResourceEvent
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import org.axonframework.modelling.command.EntityId

class ChoiceResourceEntity() {

    @EntityId
    lateinit var id: ResourceIdentifier

    var changeValue: Int = 0

    constructor(id: ResourceIdentifier, changeValue: Int) : this() {
        this.id = id
        this.changeValue = changeValue
    }

    fun on(event: UpdatedChoiceResourceEvent){
        changeValue = event.changeValue
    }
}
