package com.esgi.nova.core.events

import com.esgi.nova.core_api.events.commands.UpdateEventTranslationCommand
import com.esgi.nova.core_api.events.events.UpdatedEventTranslationEvent
import com.esgi.nova.core_api.languages.LanguageIdentifier
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.EntityId

class EventTranslationEntity() {

    @EntityId
    lateinit var id: LanguageIdentifier

    lateinit var title: String
    lateinit var description: String

    constructor(id: LanguageIdentifier, title: String, description: String) : this() {
        this.id = id
        this.title = title
        this.description = description
    }

    @CommandHandler
    fun handle(cmd: UpdateEventTranslationCommand) {
        AggregateLifecycle.apply(
            UpdatedEventTranslationEvent(
                eventId = cmd.eventId,
                translationId = cmd.translationId,
                title = cmd.title,
                description = cmd.description
            )
        )
    }

    @EventSourcingHandler
    fun on(event: UpdatedEventTranslationEvent) {
        this.title = event.title
        this.description = event.description
    }

}
