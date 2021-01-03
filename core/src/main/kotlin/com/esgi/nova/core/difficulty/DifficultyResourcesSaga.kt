package com.esgi.nova.core.difficulty

import com.esgi.nova.core_api.difficulty.events.DeletedDifficultyEvent
import com.esgi.nova.core_api.games.commands.DeleteGameCommand
import com.esgi.nova.core_api.games.commands.GameIdentifier
import com.esgi.nova.core_api.games.events.CreatedGameEvent
import com.esgi.nova.core_api.games.events.DeletedGameEvent
import com.esgi.nova.core_api.languages.events.LanguageDeletedEvent
import com.esgi.nova.core_api.resources.commands.DeleteResourceDifficultyCommand
import com.esgi.nova.core_api.resources.commands.DeleteResourceTranslationCommand
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import com.esgi.nova.core_api.resources.events.*
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired

@Saga
open class DifficultyResourcesSaga constructor() {

    @Transient
    @Autowired
    lateinit var commandGateway: CommandGateway
    private lateinit var resourceId: ResourceIdentifier;

    @StartSaga
    @SagaEventHandler(associationProperty = "difficultyId")
    fun handle(event: CreatedResourceDifficultyEvent) {
        resourceId = event.resourceId;
        SagaLifecycle.associateWith("resourceId", event.resourceId.toString())
    }

    @SagaEventHandler(associationProperty = "difficultyId")
    fun on(event: DeletedDifficultyEvent) {
        commandGateway.send<DeleteResourceTranslationCommand>(
            DeleteResourceDifficultyCommand(
                resourceId = resourceId,
                difficultyId = event.difficultyId
            )
        )
    }

    @SagaEventHandler(associationProperty = "difficultyId")
    fun on(event: DeletedResourceDifficultyEvent) {
        SagaLifecycle.end()
    }

    @SagaEventHandler(associationProperty = "resourceId")
    fun on(event: DeletedResourceEvent) {
        SagaLifecycle.end()
    }
}