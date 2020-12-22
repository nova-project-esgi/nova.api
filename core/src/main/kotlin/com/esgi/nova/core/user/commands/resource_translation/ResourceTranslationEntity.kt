package com.esgi.nova.core.user.commands.resource_translation

import com.esgi.nova.core_api.resource_translation.commands.ResourceTranslationIdentifier
import com.esgi.nova.core_api.resource_translation.commands.UpdateResourceTranslationCommand
import com.esgi.nova.core_api.resource_translation.events.UpdatedResourceTranslationEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.EntityId

class ResourceTranslationEntity() {

    @EntityId
    lateinit var id: ResourceTranslationIdentifier

    lateinit var name: String

    constructor(id: ResourceTranslationIdentifier, name: String) : this() {
        this.id = id
        this.name = name
    }

    @CommandHandler
    fun handle(cmd: UpdateResourceTranslationCommand) {
        AggregateLifecycle.apply(
            UpdatedResourceTranslationEvent(
                resourceId = cmd.id,
                translationId = cmd.translationId,
                name = cmd.name
            )
        )
    }

    @EventSourcingHandler
    fun on(event: UpdatedResourceTranslationEvent){
        this.name = event.name
    }


}
