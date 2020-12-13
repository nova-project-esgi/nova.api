package com.esgi.nova.query.user.query_handlers

import com.esgi.nova.core_api.user.queries.HasUsersQuery
import com.esgi.nova.query.user.UserRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class HasUsersHandler(private val userRepository: UserRepository) {

    @QueryHandler
    fun handle(query: HasUsersQuery): Boolean {
        return userRepository.count() > 0
    }

}