package com.esgi.nova.query.user.query_handlers

import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.user.queries.FindPaginatedUserUsernamesByUsernameQuery
import com.esgi.nova.core_api.user.views.UserUsername
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import com.esgi.nova.query.user.UserRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedUserUsernamesByUsernameHandler(private val userRepository: UserRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedUserUsernamesByUsernameQuery): PageBase<UserUsername> {
        return userRepository.getAllByUsernameStartingWith(query.username, query.toPageable()).map {
            it.toUserUsername()
        }.toStaticPage(query)
    }
}