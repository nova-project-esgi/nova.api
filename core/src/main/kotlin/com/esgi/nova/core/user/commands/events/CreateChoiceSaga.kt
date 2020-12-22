package com.esgi.nova.core.user.commands.events

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.choices.events.CreatedChoiceEvent
import com.esgi.nova.core_api.event_translations.events.AddedChoiceEvent
import com.esgi.nova.core_api.events.commands.AddChoiceCommand
import com.esgi.nova.core_api.events.commands.EventIdentifier
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.send
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
    fun handle(event: CreatedChoiceEvent) {
        SagaLifecycle.associateWith("eventId", event.eventId.toString())
        val id = commandGateway.send<EventIdentifier>(AddChoiceCommand(eventId = event.eventId, choiceId = event.choiceId))
    }

    @SagaEventHandler(associationProperty = "eventId")
    fun on(event: AddedChoiceEvent){
        SagaLifecycle.end()
    }
}