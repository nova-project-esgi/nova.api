package com.esgi.nova.core.user.commands.events

import com.esgi.nova.core_api.events.commands.CreateEventCommand
import com.esgi.nova.core_api.events.commands.CreateEventTranslationCommand
import com.esgi.nova.core_api.events.events.CreatedEventTranslationEvent
import com.esgi.nova.core_api.events.events.EventCreatedEvent
import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.events.commands.EventTranslationIdentifier
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.spring.stereotype.Aggregate
import kotlin.properties.Delegates

@Aggregate
class EventAggregate() {
    @AggregateIdentifier
    private lateinit var id: EventIdentifier
    private var isDaily by Delegates.notNull<Boolean>()
    private var isActive by Delegates.notNull<Boolean>()

    @AggregateMember
    private var translations = mutableListOf<EventTranslationEntity>()

    @CommandHandler
    constructor(cmd: CreateEventCommand) : this() {
        AggregateLifecycle.apply(EventCreatedEvent(id = cmd.id, isDaily = cmd.isDaily, isActive = cmd.isActive))
    }

    @CommandHandler
    fun handle(cmd: CreateEventTranslationCommand) {
        AggregateLifecycle.apply(CreatedEventTranslationEvent(eventId = cmd.eventId,
                languageId = cmd.languageId,
                title = cmd.title,
                description = cmd.description))
    }

    @EventSourcingHandler
    fun onEventCreateEvent(event: EventCreatedEvent) {
        isActive = event.isActive
        isDaily = event.isDaily
    }

    @EventSourcingHandler
    fun onEventTranslationCreatedEvent(event: CreatedEventTranslationEvent) {
        translations.add(EventTranslationEntity(
                id = EventTranslationIdentifier(
                        eventId = event.eventId.toString(),
                        languageId = event.languageId.toString()
                ),
                title = event.title,
                description = event.description
        ))
    }
}