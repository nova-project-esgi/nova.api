package com.esgi.nova.query.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    fun findByUsername(username: String): User?
    fun findByUsernameAndPassword(username: String, password: String): User?
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}