package port_implementation

import com.google.inject.Inject
import mappers.UserMapper
import nova.api.ports.provided.UserDto
import nova.api.ports.required.IUserPersistence
import org.mapstruct.factory.Mappers
import repositories.UserRepository

class UserPersistence @Inject constructor(private val userRepository: UserRepository): IUserPersistence {
    private val userMapper = Mappers.getMapper(UserMapper::class.java)
    override fun getAllUsers(): List<UserDto> = userRepository.getAllUsers().map { userMapper.toDto(it)}

}