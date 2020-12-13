package com.esgi.nova.core_api.user.views

import com.esgi.nova.core_api.user.Role

data class UserResume(val email: String, val role: Role, val username: String)
