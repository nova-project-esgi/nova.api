package com.esgi.nova.query.user.query_handlers

import com.esgi.nova.core_api.user.queries.FindUserByUsernameQuery
import com.esgi.nova.core_api.user.views.UserCredential
import com.esgi.nova.query.user.UserRepository
import org.springframework.stereotype.Component

@Component
open class FindUserCredentialByUsernameQuery(private val userRepository: UserRepository) {
    fun handle(query: FindUserByUsernameQuery): UserCredential? {
        return userRepository.findByUsername(query.username)?.toUserCredential()
    }
}