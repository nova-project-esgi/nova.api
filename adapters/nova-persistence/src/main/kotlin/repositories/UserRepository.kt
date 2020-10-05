package repositories

import mappers.UserMapper
import models.UserEntity
import nova.api.ports.provided.UserDto
import nova.api.ports.required.IUserPersistence
import org.jetbrains.exposed.sql.transactions.transaction
import org.mapstruct.factory.Mappers

class UserRepository {
    fun getAllUsers(): List<UserEntity> = transaction { UserEntity.all().toList() }
//    fun upsertUser(user: UserDto):UserDto{
//        return transaction {
//            val userEntity = UserEntity.find()
//        }
//    }
}