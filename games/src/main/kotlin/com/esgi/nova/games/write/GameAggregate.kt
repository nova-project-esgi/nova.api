package com.esgi.nova.games.write

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import com.esgi.nova.core_api.games.GameIdentifier
import com.esgi.nova.core_api.games.commands.*
import com.esgi.nova.core_api.games.events.*
import com.esgi.nova.core_api.user.UserIdentifier
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

@Aggregate
class GameAggregate() {
    @AggregateIdentifier
    lateinit var id: GameIdentifier
    lateinit var userId: UserIdentifier
    var duration: Int = 0
    lateinit var difficultyId: DifficultyIdentifier
    var isEnded: Boolean = false
    var logger: Logger = LoggerFactory.getLogger(GameAggregate::class.java)

    @AggregateMember
    var gameResources = mutableListOf<GameResourceEntity>()

    @AggregateMember
    var gameEvents = mutableListOf<GameEventEntity>()

    @CommandHandler
    constructor(command: CreateGameCommand) : this() {
        AggregateLifecycle.apply(
            CreatedGameEvent(
                gameId = command.gameId,
                userId = command.userId,
                difficultyId = command.difficultyId
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreatedGameEvent) {
        this.duration = 0
        this.id = event.gameId
        this.userId = event.userId
        this.difficultyId = event.difficultyId
    }

    @CommandHandler
    fun handle(cmd: DeleteGameCommand) {
        AggregateLifecycle.apply(DeletedGameEvent(id))
    }

    @EventSourcingHandler
    fun on(event: DeletedGameEvent) {
        AggregateLifecycle.markDeleted()
    }

    @CommandHandler
    fun handle(cmd: AddGameResourceCommand) {
        AggregateLifecycle.apply(
            AddedGameResourceEvent(
                gameId = cmd.gameId,
                resourceId = cmd.resourceId,
                total = cmd.total
            )
        )
    }

    @EventSourcingHandler
    fun on(event: AddedGameResourceEvent) {
        gameResources.add(GameResourceEntity(id = event.resourceId, total = event.total))
    }

    @CommandHandler
    fun handle(cmd: AddGameEventCommand) {
        AggregateLifecycle.apply(
            AddedGameEventEvent(
                gameId = id,
                eventId = cmd.eventId,
                linkTime = LocalDateTime.now()
            )
        )
    }

    @CommandHandler
    fun handle(cmd: RemoveGameEventCommand) {
        AggregateLifecycle.apply(
            RemovedGameEventEvent(
                gameId = id,
                eventId = cmd.eventId
            )
        )
    }

    @EventSourcingHandler
    fun on(event: RemovedGameEventEvent) {
        gameEvents.removeIf { it.id == event.eventId }
    }

    @CommandHandler
    fun handle(cmd: RemoveGameResourceCommand) {
        AggregateLifecycle.apply(
            RemovedGameResourceEvent(
                gameId = id,
                resourceId = cmd.resourceId
            )
        )
    }

    @EventSourcingHandler
    fun on(event: RemovedGameResourceEvent) {
        gameResources.removeIf { it.id == event.resourceId }
    }


    @EventSourcingHandler
    fun on(event: AddedGameEventEvent) {
        gameEvents.add(GameEventEntity(id = event.eventId, linkTime = event.linkTime))
    }

    @CommandHandler
    fun handle(cmd: UpdateGameCommand) {
        logger.trace("Update game Command $cmd")
        logger.trace("GameEvents : $gameEvents")
        logger.trace("GameResources : $gameResources")
        AggregateLifecycle.apply(UpdatedGameEvent(gameId = id, duration = cmd.duration, isEnded = cmd.isEnded))
        logger.trace("Update game resources")
        cmd.resources.forEach { r ->
            AggregateLifecycle.apply(UpdatedGameResourceEvent(gameId = id, resourceId = r.resourceId, total = r.total))
        }
        logger.trace("Add game events")
        cmd.events
            .filter { e -> !gameEvents.any { gameEvent -> gameEvent.linkTime == e.linkTime } }
            .forEach { e ->
                AggregateLifecycle.apply(AddedGameEventEvent(gameId = id, eventId = e.eventId, linkTime = e.linkTime))
            }
    }

    @EventSourcingHandler
    fun on(event: UpdatedGameEvent) {
        duration = event.duration
        isEnded = event.isEnded
    }
}