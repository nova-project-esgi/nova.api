package nova.api.ports.provided

interface IUserService {
    fun getAllUsers(): List<UserDto>
}