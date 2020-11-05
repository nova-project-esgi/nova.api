package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.adapters.exposed.tables.User
import com.esgi.nova.ports.provided.dtos.user.UserCmdDto
import com.google.inject.Inject
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserRepository @Inject constructor(private val dbContext: DatabaseContext) {
    fun getAll(): List<UserEntity> = transaction {
        UserEntity.all().toList()
    }

    fun create(user: UserCmdDto) = transaction {
        UserEntity.new {
            password = user.password
            username = username
        }
    }

    fun getOrCreate(user: UserCmdDto): UserEntity {
        return dbContext.connectAndExec {

            val userEntity =
                UserEntity.find { (User.password eq user.password) and (User.username eq user.username) }.firstOrNull()

            userEntity ?: create(user)
        }
    }

    fun getByName(username: String): UserEntity? =
        dbContext.connectAndExec { UserEntity.find { (User.username eq username) }.singleOrNull() }

    fun getAllTotal(pagination: DatabasePagination) = transaction {
        val elements = UserEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    fun getOne(id: UUID) = transaction { UserEntity.findById(id) }

    fun updateOne(id: UUID, user: UserCmdDto) = transaction {
        getOne(id)?.also { userEntity ->
            userEntity.username = user.username
            userEntity.password = user.password
        }
    }
}