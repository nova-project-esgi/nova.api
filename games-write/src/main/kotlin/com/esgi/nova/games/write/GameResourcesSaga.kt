package com.esgi.nova.games.write

import com.esgi.nova.core_api.games.GameIdentifier
import com.esgi.nova.core_api.games.commands.RemoveGameResourceCommand
import com.esgi.nova.core_api.games.events.AddedGameResourceEvent
import com.esgi.nova.core_api.games.events.DeletedGameEvent
import com.esgi.nova.core_api.games.events.RemovedGameResourceEvent
import com.esgi.nova.core_api.resources.events.DeletedResourceEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired

@Saga
open class GameResourcesSaga constructor() {

    @Transient
    @Autowired
    lateinit var commandGateway: CommandGateway
    private lateinit var gameId: GameIdentifier;

    @StartSaga
    @SagaEventHandler(associationProperty = "resourceId")
    fun handle(event: AddedGameResourceEvent) {
        gameId = event.gameId;
        SagaLifecycle.associateWith("gameId", event.gameId.toString())
    }

    @SagaEventHandler(associationProperty = "resourceId")
    fun on(event: DeletedResourceEvent) {
        commandGateway.send<RemoveGameResourceCommand>(
            RemoveGameResourceCommand(
                resourceId = event.resourceId,
                gameId = gameId
            )
        )
    }

    @SagaEventHandler(associationProperty = "resourceId")
    fun on(event: RemovedGameResourceEvent) {
        SagaLifecycle.end()
    }

    @SagaEventHandler(associationProperty = "gameId")
    fun on(event: DeletedGameEvent) {
        SagaLifecycle.end()
    }
}