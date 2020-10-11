package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.adapters.exposed.tables.User
import com.esgi.nova.ports.provided.UserDto
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {
    fun getAllUsers(): List<UserEntity> = transaction { UserEntity.all().toList() }
    fun getOrPutUser(user: UserDto): UserEntity {
        return transaction {
            val userEntity =
                UserEntity.find { (User.password eq user.password) and (User.username eq user.username) }.firstOrNull()
            userEntity?.apply {
                username = user.username
                password = user.password
            }
                ?: UserEntity.new {
                    password = user.password
                    username = user.username
                }

        }
    }
}