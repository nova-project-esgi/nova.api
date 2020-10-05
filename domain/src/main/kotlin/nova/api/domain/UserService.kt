package nova.api.domain

import com.google.inject.Inject
import nova.api.ports.provided.IUserService
import nova.api.ports.required.IUserPersistence


class UserService @Inject constructor(private val userPersistence: IUserPersistence) : IUserService {
    override fun getAllUsers() = userPersistence.getAllUsers()
}