package nova.api.ports.required

import nova.api.ports.provided.UserDto

interface IUserPersistence {
    fun getAllUsers(): List<UserDto>
}