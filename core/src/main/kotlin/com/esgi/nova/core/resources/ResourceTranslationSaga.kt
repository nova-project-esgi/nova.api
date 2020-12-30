package com.esgi.nova.core.resources

import com.esgi.nova.core_api.languages.events.LanguageDeletedEvent
import com.esgi.nova.core_api.resource_translation.commands.DeleteResourceTranslationCommand
import com.esgi.nova.core_api.resource_translation.events.CreatedResourceTranslationEvent
import com.esgi.nova.core_api.resource_translation.events.DeletedResourceTranslationEvent
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import com.esgi.nova.core_api.resources.events.DeletedResourceEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired


@Saga
open class ResourceTranslationSaga constructor() {

    @Transient
    @Autowired
    lateinit var commandGateway: CommandGateway
    private lateinit var resourceId: ResourceIdentifier;

    @StartSaga
    @SagaEventHandler(associationProperty = "translationId")
    fun handle(event: CreatedResourceTranslationEvent) {
        resourceId = event.resourceId;
        SagaLifecycle.associateWith("resourceId", event.resourceId.toString())
        SagaLifecycle.associateWith("languageId", event.translationId.toString())
    }

    @SagaEventHandler(associationProperty = "languageId")
    fun on(event: LanguageDeletedEvent) {
        commandGateway.send<DeleteResourceTranslationCommand>(
            DeleteResourceTranslationCommand(
                resourceId = resourceId,
                translationId = event.languageId
            )
        )
    }

    @SagaEventHandler(associationProperty = "translationId")
    fun on(event: DeletedResourceTranslationEvent) {
        SagaLifecycle.end()
    }

    @SagaEventHandler(associationProperty = "resourceId")
    fun on(event: DeletedResourceEvent) {
        SagaLifecycle.end()
    }
}