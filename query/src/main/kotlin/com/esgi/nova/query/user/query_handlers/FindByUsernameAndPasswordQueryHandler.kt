package com.esgi.nova.query.user.query_handlers

import com.esgi.nova.core_api.user.queries.FindUserByUsernameAndPasswordQuery
import com.esgi.nova.core_api.user.views.UserResume
import com.esgi.nova.query.user.UserRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component


@Component
open class FindByUsernameAndPasswordQueryHandler(private val userRepository: UserRepository) {

    @QueryHandler
    fun handle(query: FindUserByUsernameAndPasswordQuery): UserResume? {
        return userRepository.findByUsernameAndPassword(query.username, query.password)?.toUserResume()
    }
}