package com.esgi.nova.adapters.web.mappers

import com.esgi.nova.adapters.web.domain.JWT
import com.esgi.nova.adapters.web.domain.UserResume
import com.esgi.nova.ports.provided.dtos.user.UserDto
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330")
abstract class UserMapper {
    fun toResume(user: UserDto, jwt: JWT): UserResume{
        return UserResume(
            token = jwt,
            id = user.id,
            username = user.username,
            email = user.email,
            role = user.role
        )
    }
//    fun toCmdDto(user: LoginRegister): UserCmdDto
}
