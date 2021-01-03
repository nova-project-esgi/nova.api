package com.esgi.nova.core.events

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.choices.commands.DeleteChoiceCommand
import com.esgi.nova.core_api.choices.events.CreatedChoiceEvent
import com.esgi.nova.core_api.choices.events.DeletedChoiceEvent
import com.esgi.nova.core_api.events.events.RemovedChoiceEvent
import com.esgi.nova.core_api.events.commands.AddChoiceCommand
import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.events.events.DeletedEventEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired

@Saga
open class EventChoiceSaga constructor() {

    @Transient @Autowired
    lateinit var commandGateway: CommandGateway
    lateinit var choiceId: ChoiceIdentifier

    @StartSaga
    @SagaEventHandler(associationProperty = "choiceId")
    fun handle(event: CreatedChoiceEvent) {
        choiceId = event.choiceId
        SagaLifecycle.associateWith("eventId", event.eventId.toString())
        commandGateway.send<EventIdentifier>(AddChoiceCommand(eventId = event.eventId, choiceId = event.choiceId))
    }

    @SagaEventHandler(associationProperty = "eventId")
    fun on(event: DeletedEventEvent){
        commandGateway.send<ChoiceIdentifier>(DeleteChoiceCommand(choiceId = choiceId))
    }

    @SagaEventHandler(associationProperty = "eventId")
    fun on(event: RemovedChoiceEvent){
        commandGateway.send<ChoiceIdentifier>(DeleteChoiceCommand(choiceId = choiceId))
    }

    @SagaEventHandler(associationProperty = "choiceId")
    fun on(event: DeletedChoiceEvent){
        SagaLifecycle.end()
    }
}