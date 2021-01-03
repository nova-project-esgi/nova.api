package com.esgi.nova.core.resources

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import com.esgi.nova.core_api.resources.commands.UpdateResourceDifficultyCommand
import com.esgi.nova.core_api.resources.events.UpdatedResourceDifficultyEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.EntityId

class ResourceDifficultyEntity() {

    @EntityId
    lateinit var id: DifficultyIdentifier
    var startValue: Int = 0

    constructor(id: DifficultyIdentifier, startValue: Int) : this() {
        this.id = id
        this.startValue = startValue
    }


    @CommandHandler
    fun handle(cmd: UpdateResourceDifficultyCommand) {
        AggregateLifecycle.apply(
            UpdatedResourceDifficultyEvent(
                resourceId = cmd.resourceId,
                difficultyId = cmd.difficultyId,
                startValue = cmd.startValue
            )
        )
    }

    @EventSourcingHandler
    fun on(event: UpdatedResourceDifficultyEvent) {
        this.startValue = event.startValue
    }

}
