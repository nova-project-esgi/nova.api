package com.esgi.nova.core.difficulty

import com.esgi.nova.core.resources.ResourceTranslationMinimalSizeException
import com.esgi.nova.core_api.difficulty.commands.*
import com.esgi.nova.core_api.difficulty.events.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class DifficultyAggregate constructor() {
    @AggregateIdentifier
    private lateinit var id: DifficultyIdentifier

    @AggregateMember
    private var translations: MutableList<DifficultyTranslationEntity> = mutableListOf()

    private var isDefault: Boolean = false

    private var rank: Int = 0

    @CommandHandler
    constructor(cmd: CreateDifficultyCommand) : this() {
        AggregateLifecycle.apply(
            CreatedDifficultyEvent(
                difficultyId = cmd.difficultyId,
                isDefault = cmd.isDefault,
                rank = cmd.rank
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreatedDifficultyEvent) {
        id = event.difficultyId
        isDefault = event.isDefault
        rank = event.rank
    }

    @CommandHandler
    fun handle(cmd: UpdateDifficultyCommand) {
        updateTranslations(cmd)
        AggregateLifecycle.apply(
            UpdatedDifficultyEvent(
                difficultyId = cmd.difficultyId,
                rank = cmd.rank,
                isDefault = cmd.isDefault
            )
        )
    }

    @EventSourcingHandler
    fun on(event: UpdatedDifficultyEvent){
        rank = event.rank
        isDefault = event.isDefault
    }

    private fun updateTranslations(cmd: UpdateDifficultyCommand) {
        val translationsForCreation = mutableListOf<DifficultyTranslationEditionDto>()
        val translationsForUpdate = mutableListOf<DifficultyTranslationEditionDto>()
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
                    DeletedDifficultyTranslationEvent(
                        difficultyId = id,
                        translationId = translationId
                    )
                )
            }
        }
        translationsForCreation.forEach { t ->
            AggregateLifecycle.apply(
                CreatedDifficultyTranslationEvent(
                    difficultyId = id, translationId = t.translationId, name = t.name
                )
            )
        }
        translationsForUpdate.forEach { t ->
            AggregateLifecycle.apply(
                UpdatedDifficultyTranslationEvent(
                    difficultyId = id, translationId = t.translationId, name = t.name
                )
            )
        }
    }

    @CommandHandler
    fun handle(cmd: CreateDifficultyTranslationCommand) {
        AggregateLifecycle.apply(
            CreatedDifficultyTranslationEvent(
                difficultyId = cmd.difficultyId,
                translationId = cmd.translationId,
                name = cmd.name
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreatedDifficultyTranslationEvent) {
        this.translations.add(DifficultyTranslationEntity(id = event.translationId, name = event.name))
    }


    @CommandHandler
    fun handle(command: DeleteDifficultyCommand) {
        AggregateLifecycle.apply(DeletedDifficultyEvent(difficultyId = command.difficultyId))
    }

    @EventSourcingHandler
    fun on(event: DeletedDifficultyEvent) {
        AggregateLifecycle.markDeleted()
    }

    @CommandHandler
    fun handle(command: DeleteDifficultyTranslationCommand) {
        if (translations.size == 1) {
            throw DifficultyTranslationMinimalSizeException()
        }
        AggregateLifecycle.apply(
            DeletedDifficultyTranslationEvent(
                difficultyId = command.difficultyId,
                translationId = command.translationId
            )
        )
    }

    @EventSourcingHandler
    fun on(event: DeletedDifficultyTranslationEvent) {
        this.translations.removeIf { it.id == event.translationId }
    }

}