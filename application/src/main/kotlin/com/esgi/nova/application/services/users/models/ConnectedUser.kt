package com.esgi.nova.application.services.users.models

import com.esgi.nova.core_api.user.Role

data class ConnectedUser(val email: String, val role: Role, val username: String, val token: String)
