package com.esgi.nova.core.events

import com.esgi.nova.core.choices.ChoiceEntity
import com.esgi.nova.core.resources.ResourceTranslationMinimalSizeException
import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.event_translations.commands.CreateEventTranslationCommand
import com.esgi.nova.core_api.event_translations.commands.DeleteEventTranslationCommand
import com.esgi.nova.core_api.event_translations.events.*
import com.esgi.nova.core_api.events.commands.*
import com.esgi.nova.core_api.events.events.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class EventAggregate() {

    @AggregateIdentifier
    private lateinit var id: EventIdentifier
    private var isDaily = false
    private var isActive = false

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
    @EventSourcingHandler
    fun on(event: CreatedEventEvent) {
        id = event.eventId
        isActive = event.isActive
        isDaily = event.isDaily
    }

    @CommandHandler
    fun handle(cmd: AddChoiceCommand) {
        AggregateLifecycle.apply(AddedChoiceEvent(eventId = cmd.eventId, choiceId = cmd.choiceId))
    }

    @EventSourcingHandler
    fun on(event: AddedChoiceEvent) {
        choices.add(ChoiceEntity(event.choiceId))
    }

    @CommandHandler
    fun handle(cmd: RemoveChoiceCommand) {
        if (choices.any { it.id == cmd.choiceId } && choices.size == 1) {
            throw EventChoicesMinimalSizeException()
        }
        AggregateLifecycle.apply(RemovedChoiceEvent(eventId = cmd.eventId, choiceId = cmd.choiceId))
    }

    @CommandHandler
    fun handle(cmd: RemovedChoiceEvent) {
        choices.removeIf { it.id == cmd.choiceId}
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
        updateTranslations(cmd)
        updateChoices(cmd)
        AggregateLifecycle.apply(UpdatedEventEvent(eventId = id, isDaily = cmd.isDaily, isActive = cmd.isActive))
    }

    private fun updateChoices(cmd: UpdateEventCommand){
        val choiceForCreation = mutableListOf<ChoiceIdentifier>()
        val choicesForUpdate = mutableListOf<ChoiceIdentifier>()
        val choicesDeletedIds = choices
            .filter { choice ->
                cmd.choiceIds.none { choiceEditionId -> choiceEditionId == choice.id }
            }
            .map { t -> t.id }
        cmd.choiceIds.forEach { choiceEdition ->
            val choice =
                choices.firstOrNull { choiceEntity -> choiceEdition == choiceEntity.id }
            if (choice == null) {
                choiceForCreation.add(choiceEdition)
            } else {
                choicesForUpdate.add(choiceEdition)
            }
        }
        if (choicesDeletedIds.size == this.choices.size || cmd.choiceIds.isEmpty()) {
            throw ResourceTranslationMinimalSizeException()
        } else {
            choicesDeletedIds.forEach { choiceId ->
                AggregateLifecycle.apply(
                    RemovedChoiceEvent(
                        eventId = id,
                        choiceId = choiceId
                    )
                )
            }
        }
        choiceForCreation.forEach { t ->
            AggregateLifecycle.apply(
                AddedChoiceEvent(
                    eventId = id, choiceId = t
                )
            )
        }
    }


    private fun updateTranslations(cmd: UpdateEventCommand) {
        val translationsForCreation = mutableListOf<EventTranslationEditionDto>()
        val translationsForUpdate = mutableListOf<EventTranslationEditionDto>()
        val translationsDeleteIds = translations
            .filter { translation ->
                cmd.translations.none { translationEdition -> translationEdition.translationId == translation.id }
            }
            .map { t -> t.id }
        cmd.translations.forEach { translationEdition ->
            val translation =
                translations.firstOrNull { translation -> translationEdition.translationId == translation.id }
            if (translation == null) {
                translationsForCreation.add(translationEdition)
            } else {
                translationsForUpdate.add(translationEdition)
            }
        }
        if (translationsDeleteIds.size == this.translations.size || cmd.translations.isEmpty()) {
            throw ResourceTranslationMinimalSizeException()
        } else {
            translationsDeleteIds.forEach { translationId ->
                AggregateLifecycle.apply(
                    DeletedEventTranslationEvent(
                        eventId = id,
                        translationId = translationId
                    )
                )
            }
        }
        translationsForCreation.forEach { t ->
            AggregateLifecycle.apply(
                CreatedEventTranslationEvent(
                    eventId = id, translationId = t.translationId, title = t.title, description = t.description
                )
            )
        }
        translationsForUpdate.forEach { t ->
            AggregateLifecycle.apply(
                UpdatedEventTranslationEvent(
                    eventId = id, translationId = t.translationId, title = t.title, description = t.description
                )
            )
        }
    }

    @EventSourcingHandler
    fun on(event: UpdatedEventEvent){
        isActive = event.isActive
        isDaily = event.isDaily
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