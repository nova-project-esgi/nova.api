package com.esgi.nova.core.events

import com.esgi.nova.core_api.choice_translations.commands.DeleteChoiceTranslationCommand
import com.esgi.nova.core_api.event_translations.commands.DeleteEventTranslationCommand
import com.esgi.nova.core_api.event_translations.events.CreatedEventTranslationEvent
import com.esgi.nova.core_api.event_translations.events.DeletedEventTranslationEvent
import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.events.events.DeletedEventEvent
import com.esgi.nova.core_api.languages.events.LanguageDeletedEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired

@Saga
open class EventTranslationSaga constructor() {

    @Transient
    @Autowired
    lateinit var commandGateway: CommandGateway
    private lateinit var eventId: EventIdentifier;

    @StartSaga
    @SagaEventHandler(associationProperty = "translationId")
    fun handle(event: CreatedEventTranslationEvent) {
        eventId = event.eventId;
        SagaLifecycle.associateWith("eventId", event.eventId.toString())
        SagaLifecycle.associateWith("languageId", event.translationId.toString())
    }

    @SagaEventHandler(associationProperty = "languageId")
    fun on(event: LanguageDeletedEvent) {
        commandGateway.send<DeleteChoiceTranslationCommand>(
            DeleteEventTranslationCommand(
                eventId = eventId,
                translationId = event.languageId
            )
        )
    }

    @SagaEventHandler(associationProperty = "translationId")
    fun on(event: DeletedEventTranslationEvent) {
        SagaLifecycle.end()
    }

    @SagaEventHandler(associationProperty = "eventId")
    fun on(event: DeletedEventEvent) {
        SagaLifecycle.end()
    }
}