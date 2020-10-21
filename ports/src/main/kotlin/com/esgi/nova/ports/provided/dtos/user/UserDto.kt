package com.esgi.nova.ports.provided.dtos.user

import com.esgi.nova.ports.provided.enums.Role
import com.github.pozo.KotlinBuilder
import java.util.*

class UserDto(var id: UUID, var username: String, var password: String, var role: Role)