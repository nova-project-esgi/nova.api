package com.esgi.nova.core_api.user.commands

import com.esgi.nova.core_api.user.Role
import com.esgi.nova.core_api.user.UserIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateUserRoleCommand(@TargetAggregateIdentifier val userId: UserIdentifier, val role: Role)
