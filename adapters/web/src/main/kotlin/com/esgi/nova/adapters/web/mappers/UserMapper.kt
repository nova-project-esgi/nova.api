package com.esgi.nova.adapters.web.mappers

import com.esgi.nova.adapters.web.models.LoginRegister
import com.esgi.nova.ports.provided.dtos.UserDto
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330")
interface UserMapper {
    fun toDto(user: LoginRegister): UserDto
}
