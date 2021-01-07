package com.esgi.nova.choices.write

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.choices.commands.DeleteChoiceResourceCommand
import com.esgi.nova.core_api.choices.events.CreatedChoiceResourceEvent
import com.esgi.nova.core_api.choices.events.DeletedChoiceEvent
import com.esgi.nova.core_api.choices.events.DeletedChoiceResourceEvent
import com.esgi.nova.core_api.resources.ResourceIdentifier
import com.esgi.nova.core_api.resources.events.DeletedResourceEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired

@Saga
open class ChoiceResourceSaga constructor() {

    @Transient
    @Autowired
    lateinit var commandGateway: CommandGateway
    lateinit var choiceId: ChoiceIdentifier
    lateinit var choiceResourceId: ResourceIdentifier

    @StartSaga
    @SagaEventHandler(associationProperty = "choiceResourceId")
    fun handle(event: CreatedChoiceResourceEvent) {
        choiceId = event.choiceId
        SagaLifecycle.associateWith("choiceId", event.choiceId.toString())
        SagaLifecycle.associateWith("resourceId", event.choiceResourceId.toString())
    }


    @SagaEventHandler(associationProperty = "resourceId")
    fun on(event: DeletedResourceEvent) {
        commandGateway.send<ChoiceIdentifier>(
            DeleteChoiceResourceCommand(
                choiceId = choiceId,
                choiceResourceId = event.resourceId
            )
        )
    }

    @SagaEventHandler(associationProperty = "choiceResourceId")
    fun on(event: DeletedChoiceResourceEvent) {
        SagaLifecycle.end()
    }

    @SagaEventHandler(associationProperty = "choiceId")
    fun on(event: DeletedChoiceEvent) {
        SagaLifecycle.end()
    }
}