package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.adapters.exposed.tables.User
import com.esgi.nova.ports.provided.dtos.user.UserRegisterCmdDto
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserRepository : IRepository<UUID, UserRegisterCmdDto, UserEntity> {

    fun getByName(username: String): UserEntity? =
            transaction { UserEntity.find { (User.username eq username) }.singleOrNull() }

    fun getByUsernameAndPassword(username: String, password: String): UserEntity? =
            transaction { UserEntity.find { (User.username eq username) and (User.password eq password) }.singleOrNull() }


    //region repository
    override fun getAll() = transaction {
        UserEntity.all()
    }

    override fun create(element: UserRegisterCmdDto) = transaction {
        UserEntity.new {
            password = element.password
            username = username
        }
    }
    override fun getAllTotal(pagination: DatabasePagination) = transaction {
        val elements = UserEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    override fun getOne(id: UUID) = transaction { UserEntity.findById(id) }

    override fun updateOne(element: UserRegisterCmdDto, id: UUID): UserEntity? = transaction {
        getOne(id)?.also { userEntity ->
            userEntity.username = element.username
            userEntity.password = element.password
        }
    }

    //endregion

}