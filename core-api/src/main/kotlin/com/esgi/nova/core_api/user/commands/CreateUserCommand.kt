package com.esgi.nova.core_api.user.commands

import com.esgi.nova.core_api.user.Role
import com.esgi.nova.core_api.user.UserIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateUserCommand(
        @TargetAggregateIdentifier val userId: UserIdentifier,
        val email: String,
        val username: String,
        val password: String,
        val role: Role
) {
}