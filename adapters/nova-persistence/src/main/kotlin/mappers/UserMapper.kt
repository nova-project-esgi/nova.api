package mappers
import models.UserEntity
import nova.api.ports.provided.UserDto
import org.mapstruct.Mapper

@Mapper
interface UserMapper {
    fun toDto(user: UserEntity): UserDto
}