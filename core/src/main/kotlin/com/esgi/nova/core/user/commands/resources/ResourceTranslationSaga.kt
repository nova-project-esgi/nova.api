package com.esgi.nova.core.user.commands.resources

import com.esgi.nova.core_api.choices.events.CreatedChoiceEvent
import com.esgi.nova.core_api.event_translations.events.AddedChoiceEvent
import com.esgi.nova.core_api.events.commands.AddChoiceCommand
import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.resource_translation.events.CreatedResourceTranslationEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired


@Saga
open class CreateChoiceSaga constructor() {

    @Transient @Autowired
    lateinit var commandGateway: CommandGateway

    @StartSaga
    @SagaEventHandler(associationProperty = "choiceId")
    fun handle(event: CreatedResourceTranslationEvent) {
        SagaLifecycle.associateWith("eventId", event.eventId.toString())
        val id = commandGateway.send<EventIdentifier>(AddChoiceCommand(eventId = event.eventId, choiceId = event.choiceId))
    }

    @SagaEventHandler(associationProperty = "eventId")
    fun on(event: AddedChoiceEvent){
        SagaLifecycle.end()
    }
}