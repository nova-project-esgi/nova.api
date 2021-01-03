package com.esgi.nova.query.user.query_handlers

import com.esgi.nova.core_api.user.queries.FindUserByEmailQuery
import com.esgi.nova.core_api.user.views.UserResume
import com.esgi.nova.query.user.UserRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindUserByEmailHandler(private val userRepository: UserRepository) {

    @QueryHandler
    fun handle(query: FindUserByEmailQuery): UserResume? {
        return userRepository.findByEmail(query.email)?.toUserResume()
    }
}

