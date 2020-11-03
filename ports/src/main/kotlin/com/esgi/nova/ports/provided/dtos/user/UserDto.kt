package com.esgi.nova.ports.provided.dtos.user

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.enums.Role
import java.util.*

class UserDto(
    override var id: UUID,
    override var username: String,
    override var password: String,
    override var role: Role
) : IUser, IId<UUID>