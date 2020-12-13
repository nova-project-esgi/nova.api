package com.esgi.nova.core_api.user.queries

import com.esgi.nova.core_api.user.Role


class UserHasSameRoleQuery(val email: String, val role: Iterable<Role>) {
}