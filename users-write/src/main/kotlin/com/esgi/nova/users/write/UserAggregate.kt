package com.esgi.nova.users.write

import com.esgi.nova.core.domain.users.exceptions.UserNameHasNotChangedException
import com.esgi.nova.core_api.user.Role
import com.esgi.nova.core_api.user.UserIdentifier
import com.esgi.nova.core_api.user.commands.CreateUserCommand
import com.esgi.nova.core_api.user.commands.DeleteUserCommand
import com.esgi.nova.core_api.user.commands.UpdateUserCommand
import com.esgi.nova.core_api.user.commands.UpdateUserRoleCommand
import com.esgi.nova.core_api.user.events.UserCreatedEvent
import com.esgi.nova.core_api.user.events.UserDeletedEvent
import com.esgi.nova.core_api.user.events.UserUpdatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class UserAggregate constructor() {

    @AggregateIdentifier
    private lateinit var id: UserIdentifier

    private lateinit var email: String
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var role: Role

    @CommandHandler
    constructor(command: CreateUserCommand) : this() {
        AggregateLifecycle.apply(
            UserCreatedEvent(
                userId = command.userId,
                username = command.username,
                password = command.password,
                role = command.role,
                email = command.email
            )
        )
    }

    // Event handlers
    @EventSourcingHandler
    fun on(event: UserCreatedEvent) {
        id = event.userId
        username = event.username
        role = event.role
        password = event.password
        email = event.email
    }


    fun validateUsernameIsDifferent(providedUsername: String) {
        if (username == providedUsername) {
            throw UserNameHasNotChangedException(username)
        }
    }

    @CommandHandler
    fun handle(command: DeleteUserCommand) {
        AggregateLifecycle.apply(UserDeletedEvent(userId = command.userId))
    }

    // Event handlers
    @EventSourcingHandler
    fun on(event: UserDeletedEvent) {
        AggregateLifecycle.markDeleted()
    }


    @CommandHandler
    fun handle(command: UpdateUserCommand) {
        validateUsernameIsDifferent(username)
        AggregateLifecycle.apply(
            UserUpdatedEvent(
                userId = command.userId,
                username = command.username,
                password = command.password,
                role = role,
                email = command.email
            )
        )
    }

    @EventSourcingHandler
    fun on(event: UserUpdatedEvent) {
        email = event.email
        username = event.username
        role = event.role
        password = event.password
    }

    @CommandHandler
    fun handle(cmd: UpdateUserRoleCommand) {
        AggregateLifecycle.apply(
            UserUpdatedEvent(
                userId = id,
                username = username,
                email = email,
                password = password,
                role = cmd.role
            )
        )
    }

}


