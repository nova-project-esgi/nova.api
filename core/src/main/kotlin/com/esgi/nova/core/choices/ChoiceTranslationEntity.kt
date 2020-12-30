package com.esgi.nova.core.choices

import com.esgi.nova.core_api.choice_translations.events.UpdatedChoiceTranslationEvent
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.resource_translation.commands.UpdateResourceTranslationCommand
import com.esgi.nova.core_api.resource_translation.events.UpdatedResourceTranslationEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.EntityId


class ChoiceTranslationEntity() {

    @EntityId
    lateinit var id: LanguageIdentifier
    lateinit var title: String
    lateinit var description: String


    constructor(id: LanguageIdentifier, title: String, description: String) : this() {
        this.id = id
        this.description = description
        this.title = title
    }


    @EventSourcingHandler
    fun on(event: UpdatedChoiceTranslationEvent){
        this.title = event.title
        this.description = event.description
    }


}
