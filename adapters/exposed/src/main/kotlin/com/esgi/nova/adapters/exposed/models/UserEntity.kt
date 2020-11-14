package com.esgi.nova.adapters.exposed.models

import com.esgi.nova.adapters.exposed.tables.User
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserEntity>(User)

    var username by User.username
    var password by User.password
    var email by User.email
    var role by User.role
}