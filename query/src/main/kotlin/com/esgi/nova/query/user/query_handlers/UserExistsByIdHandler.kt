package com.esgi.nova.query.user.query_handlers

import com.esgi.nova.core_api.user.queries.UserExistsByIdQuery
import com.esgi.nova.query.user.UserRepository
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class UserExistsByIdHandler(private val userRepository: UserRepository) {

    @QueryHandler
    fun handle(query: UserExistsByIdQuery): Boolean {
        return userRepository.existsById(query.id.toUUID())
    }
}