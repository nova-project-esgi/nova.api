package com.esgi.nova.core.user.commands.events

import com.esgi.nova.core.user.commands.choices.ChoiceEntity
import com.esgi.nova.core.user.commands.resources.ResourceTranslationMinimalSizeException
import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.event_translations.commands.CreateEventTranslationCommand
import com.esgi.nova.core_api.event_translations.commands.DeleteEventTranslationCommand
import com.esgi.nova.core_api.event_translations.commands.EventTranslationIdentifier
import com.esgi.nova.core_api.event_translations.events.AddedChoiceEvent
import com.esgi.nova.core_api.event_translations.events.CreatedEventTranslationEvent
import com.esgi.nova.core_api.event_translations.events.DeletedEventTranslationEvent
import com.esgi.nova.core_api.event_translations.events.UpdatedEventTranslationEvent
import com.esgi.nova.core_api.events.commands.*
import com.esgi.nova.core_api.events.events.CreatedEventEvent
import com.esgi.nova.core_api.events.events.DeletedEventEvent
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

    @AggregateMember
    private var choices = mutableListOf<ChoiceEntity>()

    @CommandHandler
    constructor(cmd: CreateEventCommand) : this() {
        AggregateLifecycle.apply(
            CreatedEventEvent(
                eventId = cmd.eventId,
                isDaily = cmd.isDaily,
                isActive = cmd.isActive
            )
        )
    }

    @CommandHandler
    fun handle(cmd: AddChoiceCommand) {
        AggregateLifecycle.apply(AddedChoiceEvent(eventId = cmd.eventId, choiceId = cmd.choiceId))
    }

    @EventSourcingHandler
    fun on(event: AddedChoiceEvent) {
        choices.add(ChoiceEntity(event.choiceId))
    }

    @EventSourcingHandler
    fun on(event: CreatedEventEvent) {
        id = event.eventId
        isActive = event.isActive
        isDaily = event.isDaily
    }

    fun addChoice(choiceId: ChoiceIdentifier) {
        choices.add(ChoiceEntity(choiceId))
    }

    @CommandHandler
    fun handle(cmd: DeleteEventCommand) {
        AggregateLifecycle.apply(DeletedEventEvent(eventId = cmd.eventId))
    }

    @EventSourcingHandler
    fun on(event: DeletedEventEvent) {
        AggregateLifecycle.markDeleted()
    }

    @CommandHandler
    fun handle(cmd: UpdateEventCommand) {
        val translationEditionIds = mutableListOf<EventTranslationIdentifier>()
        cmd.translations.forEach { translationEdition ->
            val translation =
                translations.firstOrNull { translation -> translationEdition.translationId == translation.id }
            if (translation == null) {
                AggregateLifecycle.apply(
                    CreatedEventTranslationEvent(
                        eventId = id,
                        translationId = translationEdition.translationId,
                        title = translationEdition.title,
                        description = translationEdition.description
                    )
                )
            } else {
                translationEditionIds.add(translation.id)
                AggregateLifecycle.apply(
                    UpdatedEventTranslationEvent(
                        eventId = id,
                        translationId = translationEdition.translationId,
                        title = translationEdition.title,
                        description = translationEdition.description
                    )
                )
            }
        }
        val translationDeleteIds = mutableListOf<EventTranslationIdentifier>()
        translations.forEach { translation ->
            if (translationEditionIds.none { translationId -> translation.id == translationId }) {
                translationDeleteIds += translation.id
            }
        }
        if (translationDeleteIds.size == this.translations.size && cmd.translations.isEmpty()) {
            throw ResourceTranslationMinimalSizeException()
        } else {
            translationDeleteIds.forEach { translationId ->
                AggregateLifecycle.apply(DeletedEventTranslationEvent(eventId = id, translationId = translationId))
            }
        }
    }

    @CommandHandler
    fun handle(cmd: CreateEventTranslationCommand) {
        AggregateLifecycle.apply(
            CreatedEventTranslationEvent(
                eventId = cmd.eventId,
                translationId = cmd.translationId,
                title = cmd.title,
                description = cmd.description
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreatedEventTranslationEvent) {
        translations.add(
            EventTranslationEntity(
                id = event.translationId,
                title = event.title,
                description = event.description
            )
        )
    }

    @CommandHandler
    fun handle(cmd: DeleteEventTranslationCommand) {
        if (translations.any { it.id == cmd.translationId } && translations.size == 1) {
            throw EventTranslationMinimalSizeException()
        }
        AggregateLifecycle.apply(DeletedEventTranslationEvent(eventId = cmd.eventId, translationId = cmd.translationId))
    }

    @EventSourcingHandler
    fun on(event: DeletedEventTranslationEvent) {
        translations.removeIf { it.id == event.translationId }
    }
}