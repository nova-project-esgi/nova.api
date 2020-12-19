package com.esgi.nova.core.user.commands.resources

import com.esgi.nova.core_api.resources.commands.*
import com.esgi.nova.core_api.resources.events.CreatedResourceEvent
import com.esgi.nova.core_api.resources.events.DeletedResourceEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ResourceAggregate constructor() {
    @AggregateIdentifier
    private lateinit var id: ResourceIdentifier


    @CommandHandler
    constructor(cmd: CreateResourceCommand) : this() {
        AggregateLifecycle.apply(CreatedResourceEvent(id = cmd.id))
    }

    @EventSourcingHandler
    fun onCreatedResourceEvent(event: CreatedResourceEvent) {
        id = event.id
    }

    @EventSourcingHandler
    fun onDeletedResourceEvent(event: DeletedResourceEvent) {
        AggregateLifecycle.markDeleted();
    }

    @CommandHandler
    fun onDeleteResourceCommand(command: DeleteResourceCommand) {
        AggregateLifecycle.apply(DeletedResourceEvent(id = command.id))
    }

}