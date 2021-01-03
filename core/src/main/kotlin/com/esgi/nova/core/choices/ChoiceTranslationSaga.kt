package com.esgi.nova.core.choices

import com.esgi.nova.core_api.choices.commands.DeleteChoiceTranslationCommand
import com.esgi.nova.core_api.choices.events.CreatedChoiceTranslationEvent
import com.esgi.nova.core_api.choices.events.DeletedChoiceTranslationEvent
import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.choices.events.DeletedChoiceEvent
import com.esgi.nova.core_api.languages.events.LanguageDeletedEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired

@Saga
open class ChoiceTranslationSaga constructor() {

    @Transient
    @Autowired
    lateinit var commandGateway: CommandGateway
    private lateinit var choiceId: ChoiceIdentifier;

    @StartSaga
    @SagaEventHandler(associationProperty = "translationId")
    fun handle(event: CreatedChoiceTranslationEvent) {
        choiceId = event.choiceId;
        SagaLifecycle.associateWith("choiceId", event.choiceId.toString())
        SagaLifecycle.associateWith("languageId", event.translationId.toString())
    }

    @SagaEventHandler(associationProperty = "languageId")
    fun on(event: LanguageDeletedEvent) {
        commandGateway.send<DeleteChoiceTranslationCommand>(
            DeleteChoiceTranslationCommand(
                choiceId = choiceId,
                translationId = event.languageId
            )
        )
    }

    @SagaEventHandler(associationProperty = "translationId")
    fun on(event: DeletedChoiceTranslationEvent) {
        SagaLifecycle.end()
    }

    @SagaEventHandler(associationProperty = "choiceId")
    fun on(event: DeletedChoiceEvent) {
        SagaLifecycle.end()
    }
}