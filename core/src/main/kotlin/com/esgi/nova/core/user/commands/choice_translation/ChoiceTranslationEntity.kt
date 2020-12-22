package com.esgi.nova.core.user.commands.choice_translation

import com.esgi.nova.core_api.choice_resource.commands.DeleteChoiceResourceCommand
import com.esgi.nova.core_api.choice_resource.events.CreatedChoiceResourceEvent
import com.esgi.nova.core_api.choice_resource.events.DeletedChoiceResourceEvent
import com.esgi.nova.core_api.choice_translations.commands.ChoiceTranslationIdentifier
import com.esgi.nova.core_api.choice_translations.commands.CreateChoiceTranslationCommand
import com.esgi.nova.core_api.choice_translations.commands.DeleteChoiceTranslationCommand
import com.esgi.nova.core_api.choice_translations.events.CreatedChoiceTranslationEvent
import com.esgi.nova.core_api.choice_translations.events.DeletedChoiceTranslationEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.EntityId
import org.axonframework.spring.stereotype.Aggregate


class ChoiceTranslationEntity() {

    @EntityId
    lateinit var id: ChoiceTranslationIdentifier
    lateinit var title: String
    lateinit var description: String


    constructor(id: ChoiceTranslationIdentifier, title: String, description: String) : this() {
        this.id = id
        this.description = description
        this.title = title
    }

}
