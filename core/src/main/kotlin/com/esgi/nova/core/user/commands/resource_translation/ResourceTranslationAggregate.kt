package com.esgi.nova.core.user.commands.resource_translation

import com.esgi.nova.core_api.resource_translation.commands.CreateResourceTranslationCommand
import com.esgi.nova.core_api.resource_translation.commands.DeleteResourceTranslationCommand
import com.esgi.nova.core_api.resource_translation.commands.ResourceTranslationIdentifier
import com.esgi.nova.core_api.resource_translation.events.CreatedResourceTranslationEvent
import com.esgi.nova.core_api.resource_translation.events.DeletedResourceTranslationEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ResourceTranslationAggregate() {

    @AggregateIdentifier
    lateinit var id: ResourceTranslationIdentifier

    lateinit var name: String

    @CommandHandler
    constructor(cmd: CreateResourceTranslationCommand) : this() {
        AggregateLifecycle.apply(CreatedResourceTranslationEvent(id = cmd.id, name = cmd.name))
    }

    @EventSourcingHandler
    fun onDeletedResourceTranslationEvent(event: DeletedResourceTranslationEvent) {
        AggregateLifecycle.markDeleted();
    }
    @EventSourcingHandler
    fun onCreatedResourceTranslationEvent(event: CreatedResourceTranslationEvent) {
        id = event.id
        name = event.name
    }

    @CommandHandler
    fun onDeleteResourceTranslationCommand(command: DeleteResourceTranslationCommand) {
        AggregateLifecycle.apply(DeletedResourceTranslationEvent(id = command.id))
    }
}
