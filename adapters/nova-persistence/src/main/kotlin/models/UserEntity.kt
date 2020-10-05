package models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.User
import java.util.*

class UserEntity(id: EntityID<UUID>): UUIDEntity(id) {
    companion object : UUIDEntityClass<UserEntity>(User)
    var username by User.username
    var password by User.password
}