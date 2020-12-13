package com.esgi.nova.query.user.query_handlers

import com.esgi.nova.core_api.user.queries.UserExistsQuery
import com.esgi.nova.query.user.UserRepository
import org.springframework.stereotype.Component

@Component
open class UserExistsQueryHandler(private val userRepository: UserRepository) {

    fun handle(query: UserExistsQuery): Boolean {
        return userRepository.existsByEmail(query.email)
    }
}