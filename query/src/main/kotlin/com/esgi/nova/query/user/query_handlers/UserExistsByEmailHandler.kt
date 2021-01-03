package com.esgi.nova.query.user.query_handlers

import com.esgi.nova.core_api.user.queries.UserExistsByEmailQuery
import com.esgi.nova.query.user.UserRepository
import org.springframework.stereotype.Component

@Component
open class UserExistsByEmailHandler(private val userRepository: UserRepository) {

    fun handle(query: UserExistsByEmailQuery): Boolean {
        return userRepository.existsByEmail(query.email)
    }
}

