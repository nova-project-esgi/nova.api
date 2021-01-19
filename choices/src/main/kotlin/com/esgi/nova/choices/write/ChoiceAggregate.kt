package com.esgi.nova.choices.write

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.choices.commands.*
import com.esgi.nova.core_api.choices.dtos.ChoiceResourceEditionDto
import com.esgi.nova.core_api.choices.dtos.ChoiceTranslationEditionDto
import com.esgi.nova.core_api.choices.events.*
import com.esgi.nova.core_api.choices.exceptions.ChoiceResourcesMinimalSizeException
import com.esgi.nova.core_api.choices.exceptions.ChoiceTranslationMinimalSizeException
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ChoiceAggregate() {
    @AggregateIdentifier
    private lateinit var id: ChoiceIdentifier

    @AggregateMember
    private var translations: MutableList<ChoiceTranslationEntity> = mutableListOf()

    @AggregateMember
    private var choiceResources: MutableList<ChoiceResourceEntity> = mutableListOf()

    @CommandHandler
    constructor(cmd: CreateChoiceCommand) : this() {
        AggregateLifecycle.apply(CreatedChoiceEvent(choiceId = cmd.choiceId, eventId = cmd.eventId))
    }

    @EventSourcingHandler
    fun on(event: CreatedChoiceEvent) {
        id = event.choiceId
    }


    @CommandHandler
    fun handle(cmd: DeleteChoiceCommand) {
        AggregateLifecycle.apply(
            DeletedChoiceEvent(
                choiceId = cmd.choiceId
            )
        )
    }

    @EventSourcingHandler
    fun on(event: DeletedChoiceEvent) {
        AggregateLifecycle.markDeleted()
    }

    @CommandHandler
    fun handle(cmd: CreateChoiceTranslationCommand) {
        AggregateLifecycle.apply(
            CreatedChoiceTranslationEvent(
                choiceId = cmd.choiceId,
                translationId = cmd.translationId,
                title = cmd.title,
                description = cmd.description
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreatedChoiceTranslationEvent) {
        this.translations.add(
            ChoiceTranslationEntity(
                id = event.translationId,
                title = event.title,
                description = event.description
            )
        )
    }


    @CommandHandler
    fun handle(cmd: DeleteChoiceTranslationCommand) {
        if (translations.any { it.id == cmd.translationId } && translations.size == 1) {
            throw ChoiceTranslationMinimalSizeException()
        }
        AggregateLifecycle.apply(
            DeletedChoiceTranslationEvent(
                choiceId = cmd.choiceId,
                translationId = cmd.translationId
            )
        )
    }

    @EventSourcingHandler
    fun on(event: DeletedChoiceTranslationEvent) {
        translations.removeIf { it.id == event.translationId }
    }

    @CommandHandler
    fun handle(cmd: CreateChoiceResourceCommand) {
        AggregateLifecycle.apply(
            CreatedChoiceResourceEvent(
                choiceId = id,
                choiceResourceId = cmd.choiceResourceId,
                changeValue = cmd.changeValue
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreatedChoiceResourceEvent) {
        choiceResources.add(
            ChoiceResourceEntity(
                id = event.choiceResourceId,
                changeValue = event.changeValue
            )
        )
    }

    @CommandHandler
    fun handle(cmd: UpdateChoiceCommand) {
        updateTranslations(cmd)
        updateResources(cmd)
    }


    @CommandHandler
    fun handle(cmd: DeleteChoiceResourceCommand) {
        if (choiceResources.any { it.id == cmd.choiceResourceId } && choiceResources.size == 1) {
            throw ChoiceResourcesMinimalSizeException()
        }
        AggregateLifecycle.apply(DeletedChoiceResourceEvent(choiceId = id, choiceResourceId = cmd.choiceResourceId))
    }

    @EventSourcingHandler
    fun on(event: DeletedChoiceResourceEvent) {
        choiceResources.removeIf { it.id == event.choiceResourceId }
    }

    private fun updateResources(cmd: UpdateChoiceCommand) {
        val choiceResourcesForCreation = mutableListOf<ChoiceResourceEditionDto>()
        val choiceResourcesForUpdate = mutableListOf<ChoiceResourceEditionDto>()
        val choiceResourcesDeleteIds = choiceResources
            .filter { choiceResource ->
                cmd.resources.none { choiceResourceEdition -> choiceResourceEdition.choiceResourceId == choiceResource.id }
            }
            .map { r -> r.id }
        cmd.resources.forEach { choiceResourceEdition ->
            val choiceResource =
                choiceResources.firstOrNull { choiceResource -> choiceResourceEdition.choiceResourceId == choiceResource.id }
            if (choiceResource == null) {
                choiceResourcesForCreation.add(choiceResourceEdition)
            } else {
                choiceResourcesForUpdate.add(choiceResourceEdition)
            }
        }
        if ((choiceResourcesDeleteIds.size == this.choiceResources.size && choiceResourcesForCreation.isEmpty())|| cmd.resources.isEmpty()) {
            throw ChoiceResourcesMinimalSizeException()
        } else {
            choiceResourcesDeleteIds.forEach { choiceResourceId ->
                AggregateLifecycle.apply(
                    DeletedChoiceResourceEvent(
                        choiceId = id,
                        choiceResourceId = choiceResourceId
                    )
                )
            }
        }
        choiceResourcesForCreation.forEach { r ->
            AggregateLifecycle.apply(
                CreatedChoiceResourceEvent(
                    choiceId = id, choiceResourceId = r.choiceResourceId, changeValue = r.changeValue
                )
            )
        }
        choiceResourcesForUpdate.forEach { r ->
            AggregateLifecycle.apply(
                UpdatedChoiceResourceEvent(
                    choiceId = id, choiceResourceId = r.choiceResourceId, changeValue = r.changeValue
                )
            )
        }
    }

    private fun updateTranslations(cmd: UpdateChoiceCommand) {
        val translationsForCreation = mutableListOf<ChoiceTranslationEditionDto>()
        val translationsForUpdate = mutableListOf<ChoiceTranslationEditionDto>()
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
            throw ChoiceTranslationMinimalSizeException()
        } else {
            translationsDeleteIds.forEach { translationId ->
                AggregateLifecycle.apply(
                    DeletedChoiceTranslationEvent(
                        choiceId = id,
                        translationId = translationId
                    )
                )
            }
        }
        translationsForCreation.forEach { t ->
            AggregateLifecycle.apply(
                CreatedChoiceTranslationEvent(
                    choiceId = id, translationId = t.translationId, title = t.title, description = t.description
                )
            )
        }
        translationsForUpdate.forEach { t ->
            AggregateLifecycle.apply(
                UpdatedChoiceTranslationEvent(
                    choiceId = id, translationId = t.translationId, title = t.title, description = t.description
                )
            )
        }
    }
}