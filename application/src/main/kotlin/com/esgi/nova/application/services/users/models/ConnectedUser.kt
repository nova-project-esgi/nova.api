package com.esgi.nova.application.services.users.models

import com.esgi.nova.core_api.user.Role
import java.util.*

data class ConnectedUser(val id: UUID, val email: String, val role: Role, val username: String, val token: String)
