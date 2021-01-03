package com.esgi.nova.query.user

import com.esgi.nova.core_api.user.Role
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    fun findByUsername(username: String): User?
    fun findByUsernameAndPassword(username: String, password: String): User?
    fun findByEmail(email: String): User?
    fun getAllByUsernameStartingWith(username: String, pageable: Pageable): Page<User>
    fun existsByEmail(email: String): Boolean
    fun getAllByRole(role: Role): List<User>
}