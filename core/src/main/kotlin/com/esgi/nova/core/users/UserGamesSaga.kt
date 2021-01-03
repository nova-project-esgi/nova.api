package com.esgi.nova.core.users

import com.esgi.nova.core_api.events.commands.DeleteEventCommand
import com.esgi.nova.core_api.games.commands.DeleteGameCommand
import com.esgi.nova.core_api.games.commands.GameIdentifier
import com.esgi.nova.core_api.games.events.CreatedGameEvent
import com.esgi.nova.core_api.games.events.DeletedGameEvent
import com.esgi.nova.core_api.resources.commands.DeleteResourceTranslationCommand
import com.esgi.nova.core_api.resources.events.DeletedResourceEvent
import com.esgi.nova.core_api.resources.events.DeletedResourceTranslationEvent
import com.esgi.nova.core_api.user.events.UserDeletedEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired

@Saga
open class UserGamesSaga constructor() {

    @Transient
    @Autowired
    lateinit var commandGateway: CommandGateway
    private lateinit var gameId: GameIdentifier;

    @StartSaga
    @SagaEventHandler(associationProperty = "gameId")
    fun handle(event: CreatedGameEvent) {
        gameId = event.gameId;
        SagaLifecycle.associateWith("userId", event.userId.toString())
    }

    @SagaEventHandler(associationProperty = "userId")
    fun on(event: UserDeletedEvent) {
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