package com.esgi.nova.resources.write

import com.esgi.nova.core_api.resources.ResourceIdentifier
import com.esgi.nova.core_api.resources.commands.*
import com.esgi.nova.core_api.resources.dtos.ResourceDifficultyEditionDto
import com.esgi.nova.core_api.resources.dtos.ResourceTranslationEditionDto
import com.esgi.nova.core_api.resources.events.*
import com.esgi.nova.core_api.resources.exceptions.ResourceDifficultiesMinimalSizeException
import com.esgi.nova.core_api.resources.exceptions.ResourceTranslationMinimalSizeException
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ResourceAggregate constructor() {
    @AggregateIdentifier
    private lateinit var id: ResourceIdentifier

    @AggregateMember
    private var translations: MutableList<ResourceTranslationEntity> = mutableListOf()

    @AggregateMember
    private var difficulties: MutableList<ResourceDifficultyEntity> = mutableListOf()

    @CommandHandler
    constructor(cmd: CreateResourceCommand) : this() {
        AggregateLifecycle.apply(CreatedResourceEvent(resourceId = cmd.resourceId))
    }

    @EventSourcingHandler
    fun on(event: CreatedResourceEvent) {
        id = event.resourceId
    }

    @CommandHandler
    fun handle(cmd: UpdateResourceCommand) {
        updateTranslations(cmd)
        updateDifficulties(cmd)
    }

    private fun updateTranslations(cmd: UpdateResourceCommand) {
        val translationsForCreation = mutableListOf<ResourceTranslationEditionDto>()
        val translationsForUpdate = mutableListOf<ResourceTranslationEditionDto>()
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
                    DeletedResourceTranslationEvent(
                        resourceId = id,
                        translationId = translationId
                    )
                )
            }
        }
        translationsForCreation.forEach { t ->
            AggregateLifecycle.apply(
                CreatedResourceTranslationEvent(
                    resourceId = id, translationId = t.translationId, name = t.name
                )
            )
        }
        translationsForUpdate.forEach { t ->
            AggregateLifecycle.apply(
                UpdatedResourceTranslationEvent(
                    resourceId = id, translationId = t.translationId, name = t.name
                )
            )
        }
    }

    private fun updateDifficulties(cmd: UpdateResourceCommand) {
        val difficultiesForCreation = mutableListOf<ResourceDifficultyEditionDto>()
        val difficultiesForUpdate = mutableListOf<ResourceDifficultyEditionDto>()
        val difficultiesDeleteIds = difficulties
            .filter { difficulty ->
                cmd.difficulties.none { difficultyEdition -> difficultyEdition.difficultyId == difficulty.id }
            }
            .map { t -> t.id }
        cmd.difficulties.forEach { difficultyEdition ->
            val difficultyEntity =
                difficulties.firstOrNull { difficulty -> difficultyEdition.difficultyId == difficulty.id }
            if (difficultyEntity == null) {
                difficultiesForCreation.add(difficultyEdition)
            } else {
                difficultiesForUpdate.add(difficultyEdition)
            }
        }
        if (difficultiesDeleteIds.size == this.difficulties.size || cmd.difficulties.isEmpty()) {
            throw ResourceDifficultiesMinimalSizeException()
        } else {
            difficultiesDeleteIds.forEach { difficultyId ->
                AggregateLifecycle.apply(
                    DeletedResourceDifficultyEvent(
                        resourceId = id,
                        difficultyId = difficultyId
                    )
                )
            }
        }
        difficultiesForUpdate.forEach { d ->
            AggregateLifecycle.apply(
                UpdatedResourceDifficultyEvent(
                    resourceId = id, difficultyId = d.difficultyId, startValue = d.startValue
                )
            )
        }
        difficultiesForCreation.forEach { d ->
            AggregateLifecycle.apply(
                CreatedResourceDifficultyEvent(
                    resourceId = id, difficultyId = d.difficultyId, startValue = d.startValue
                )
            )
        }
    }

    @CommandHandler
    fun handle(cmd: CreateResourceTranslationCommand) {
        AggregateLifecycle.apply(
            CreatedResourceTranslationEvent(
                resourceId = cmd.resourceId,
                translationId = cmd.translationId,
                name = cmd.name
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreatedResourceTranslationEvent) {
        this.translations.add(ResourceTranslationEntity(id = event.translationId, name = event.name))
    }

    @CommandHandler
    fun handle(cmd: CreateResourceDifficultyCommand) {
        AggregateLifecycle.apply(
            CreatedResourceDifficultyEvent(
                resourceId = cmd.resourceId,
                difficultyId = cmd.difficultyId,
                startValue = cmd.startValue
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreatedResourceDifficultyEvent) {
        this.difficulties.add(ResourceDifficultyEntity(id = event.difficultyId, startValue = event.startValue))
    }


    @CommandHandler
    fun handle(command: DeleteResourceCommand) {
        AggregateLifecycle.apply(DeletedResourceEvent(resourceId = command.resourceId))
    }

    @EventSourcingHandler
    fun on(event: DeletedResourceEvent) {
        AggregateLifecycle.markDeleted()
    }

    @CommandHandler
    fun handle(command: DeleteResourceTranslationCommand) {
        if (translations.size == 1) {
            throw ResourceTranslationMinimalSizeException()
        }
        AggregateLifecycle.apply(
            DeletedResourceTranslationEvent(
                resourceId = command.resourceId,
                translationId = command.translationId
            )
        )
    }

    @CommandHandler
    fun handle(command: CanDeleteResourceTranslationCommand) {
        if (translations.size == 1) {
            throw ResourceTranslationMinimalSizeException()
        }
    }

    @EventSourcingHandler
    fun on(event: DeletedResourceTranslationEvent) {
        this.translations.removeIf { it.id == event.translationId }
    }


}