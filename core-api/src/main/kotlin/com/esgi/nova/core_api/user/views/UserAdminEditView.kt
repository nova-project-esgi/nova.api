package com.esgi.nova.core_api.user.views

import com.esgi.nova.core_api.user.Role
import java.util.*

data class UserAdminEditView(
    val id: UUID,
    val email: String,
    val role: Role,
    val username: String,
    val canUpdateRole: Boolean
)
