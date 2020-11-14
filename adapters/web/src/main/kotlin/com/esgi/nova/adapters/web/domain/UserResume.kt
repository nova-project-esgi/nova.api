package com.esgi.nova.adapters.web.domain

import com.esgi.nova.ports.provided.enums.Role
import java.util.*

data class UserResume(val token: JWT, val id: UUID, val username: String, val email: String, val role: Role)

