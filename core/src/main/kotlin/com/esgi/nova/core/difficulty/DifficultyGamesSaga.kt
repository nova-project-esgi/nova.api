package com.esgi.nova.core.difficulty

import com.esgi.nova.core_api.difficulty.events.DeletedDifficultyEvent
import com.esgi.nova.core_api.games.commands.DeleteGameCommand
import com.esgi.nova.core_api.games.commands.GameIdentifier
import com.esgi.nova.core_api.games.events.CreatedGameEvent
import com.esgi.nova.core_api.games.events.DeletedGameEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired

@Saga
open class DifficultyGamesSaga constructor() {

    @Transient
    @Autowired
    lateinit var commandGateway: CommandGateway
    private lateinit var gameId: GameIdentifier;

    @StartSaga
    @SagaEventHandler(associationProperty = "gameId")
    fun handle(event: CreatedGameEvent) {
        gameId = event.gameId;
        SagaLifecycle.associateWith("difficultyId", event.difficultyId.toString())
    }

    @SagaEventHandler(associationProperty = "difficultyId")
    fun on(event: DeletedDifficultyEvent) {
        commandGateway.send<GameIdentifier>(
            DeleteGameCommand(
                gameId = gameId
            )
        )
    }

    @SagaEventHandler(associationProperty = "gameId")
    fun on(event: DeletedGameEvent) {
        SagaLifecycle.end()
    }

}

