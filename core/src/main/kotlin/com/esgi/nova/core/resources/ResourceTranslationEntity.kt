package com.esgi.nova.core.resources

import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.resources.commands.UpdateResourceTranslationCommand
import com.esgi.nova.core_api.resources.events.UpdatedResourceTranslationEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.EntityId

class ResourceTranslationEntity() {

    @EntityId
    lateinit var id: LanguageIdentifier

    lateinit var name: String

    constructor(id: LanguageIdentifier, name: String) : this() {
        this.id = id
        this.name = name
    }

    @CommandHandler
    fun handle(cmd: UpdateResourceTranslationCommand) {
        AggregateLifecycle.apply(
            UpdatedResourceTranslationEvent(
                resourceId = cmd.resourceId,
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
