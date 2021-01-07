package com.esgi.nova.games.write

import com.esgi.nova.core_api.events.EventIdentifier
import com.esgi.nova.core_api.events.events.DeletedEventEvent
import com.esgi.nova.core_api.games.GameIdentifier
import com.esgi.nova.core_api.games.commands.RemoveGameEventCommand
import com.esgi.nova.core_api.games.events.AddedGameEventEvent
import com.esgi.nova.core_api.games.events.RemovedGameEventEvent
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.SagaLifecycle
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.spring.stereotype.Saga
import org.springframework.beans.factory.annotation.Autowired

@Saga
open class GameEventsSaga constructor() {

    @Transient
    @Autowired
    lateinit var commandGateway: CommandGateway
    private lateinit var gameId: GameIdentifier;
    private lateinit var eventId: EventIdentifier;

    @StartSaga
    @SagaEventHandler(associationProperty = "gameId")
    fun handle(event: AddedGameEventEvent) {
        gameId = event.gameId;
        eventId = event.eventId;
        SagaLifecycle.associateWith("eventId", event.eventId.toString())
    }

    @SagaEventHandler(associationProperty = "eventId")
    fun on(event: DeletedEventEvent) {
        commandGateway.send<GameIdentifier>(
            RemoveGameEventCommand(
                gameId = gameId, eventId = eventId
            )
        )
    }


    @SagaEventHandler(associationProperty = "eventId")
    fun on(event: RemovedGameEventEvent) {
        SagaLifecycle.end()
    }

}


