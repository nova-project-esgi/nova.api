package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.user.UserCmdDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IUserService
import com.esgi.nova.ports.required.IUserPersistence
import com.google.inject.Inject


class UserService @Inject constructor(private val userPersistence: IUserPersistence) : IUserService {
    override fun getAll(query: Query) = userPersistence.getAll(query)
    override fun getOrCreate(userDto: UserCmdDto) = userPersistence.getOrCreate(userDto)
    override fun hasSameRole(username: String, role: Iterable<Role>): Boolean {
        val user = userPersistence.getByName(username)
        user?.let{
            return role.any{r-> r == it.role}
        }
        return false
    }
}