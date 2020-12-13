package com.esgi.nova.core_api.user.events

import com.esgi.nova.core_api.user.Role
import com.esgi.nova.core_api.user.UserIdentifier
import java.io.Serializable

data class UserCreatedEvent(
        val id: UserIdentifier,
        val email: String,
        val username: String,
        val password: String,
        val role: Role
) : Serializable {
}