package com.esgi.nova.core.user.commands

import com.esgi.nova.core_api.user.events.UserCreatedEvent
import com.esgi.nova.core.domain.users.events.UserUpdatedEvent
import com.esgi.nova.core.domain.users.exceptions.UserNameHasNotChangedException
import com.esgi.nova.core_api.user.Role
import com.esgi.nova.core_api.user.commands.CreateUserCommand
import com.esgi.nova.core_api.user.commands.DeleteUserCommand
import com.esgi.nova.core_api.user.commands.UpdateUserCommand
import com.esgi.nova.core_api.user.UserIdentifier
import com.esgi.nova.core_api.user.events.UserDeleteEvent
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
                        id = command.id,
                        username = command.username,
                        password = command.password,
                        role = command.role,
                        email = command.email
                )
        )
    }


    fun validateUsernameIsDifferent(providedUsername: String) {
        if (username == providedUsername) {
            throw UserNameHasNotChangedException(username)
        }
    }

    @CommandHandler
    fun onDeleteUserCommand(command: DeleteUserCommand) {
        AggregateLifecycle.apply(UserDeleteEvent(id = command.id))
    }

    @CommandHandler
    fun onUpdateUserCommand(command: UpdateUserCommand) {
        validateUsernameIsDifferent(username)
        AggregateLifecycle.apply(
                UserUpdatedEvent(
                        id = command.id,
                        username = command.username,
                        password = command.password,
                        role = command.role,
                        email = command.email
                )
        )
    }


    // Event handlers
    @EventSourcingHandler
    fun onUserCreatedEvent(event: UserCreatedEvent) {
        id = event.id
        username = event.username
        role = event.role
        password = event.password
        email = event.email
    }

    // Event handlers
    @EventSourcingHandler
    fun onUserDeletedEvent(event: UserDeleteEvent) {
        AggregateLifecycle.markDeleted()
    }

    @EventSourcingHandler
    fun onUserUpdatedEvent(event: UserUpdatedEvent) {
        email = event.email
        username = event.username
        role = event.role
        password = event.password
    }

//    // Command handlers
//    @CommandHandler
//    fun UserAggregate(command: CreateUser) {
//        val user: User = command.()
//        user.validateEmail()
//        AggregateLifecycle.apply(UserCreatedEvent(user))
//    }

}


