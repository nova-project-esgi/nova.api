package com.esgi.nova.events.write

import com.esgi.nova.core_api.difficulty.commands.UpdateDifficultyTranslationCommand
import com.esgi.nova.core_api.difficulty.events.UpdatedDifficultyTranslationEvent
import com.esgi.nova.core_api.languages.LanguageIdentifier
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.EntityId

class DifficultyTranslationEntity() {

    @EntityId
    lateinit var id: LanguageIdentifier

    lateinit var name: String

    constructor(id: LanguageIdentifier, name: String) : this() {
        this.id = id
        this.name = name
    }

    @CommandHandler
    fun handle(cmd: UpdateDifficultyTranslationCommand) {
        AggregateLifecycle.apply(
            UpdatedDifficultyTranslationEvent(
                difficultyId = cmd.difficultyId,
                translationId = cmd.translationId,
                name = cmd.name
            )
        )
    }

    @EventSourcingHandler
    fun on(event: UpdatedDifficultyTranslationEvent) {
        this.name = event.name
    }


}