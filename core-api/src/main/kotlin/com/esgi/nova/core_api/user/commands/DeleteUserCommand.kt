package com.esgi.nova.core_api.user.commands

import com.esgi.nova.core_api.user.UserIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteUserCommand(@TargetAggregateIdentifier val userId: UserIdentifier) {

}