package com.esgi.nova.core_api.user.views

import com.esgi.nova.core_api.user.Role
import java.util.*

data class UserResume(val id: UUID, val email: String, val role: Role, val username: String)
