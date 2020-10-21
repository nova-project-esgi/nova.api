package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.adapters.exposed.tables.User
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.user.UserCmdDto
import com.google.inject.Inject
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository @Inject constructor(private val dbContext: DatabaseContext) {
    fun getAll(query: Query): List<UserEntity> = transaction {
        UserEntity.all().toList()
    }

    fun getOrCreate(user: UserCmdDto): UserEntity {
        return dbContext.connectAndExec {

            val userEntity =
                UserEntity.find { (User.password eq user.password) and (User.username eq user.username) }.firstOrNull()

            userEntity ?: UserEntity.new {
                password = user.password
                username = user.username
            }
        }
    }

    fun getByName(username: String): UserEntity? =
        dbContext.connectAndExec { UserEntity.find { (User.username eq username) }.singleOrNull() }
}