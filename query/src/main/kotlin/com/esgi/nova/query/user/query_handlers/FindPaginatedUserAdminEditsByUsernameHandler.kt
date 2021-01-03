package com.esgi.nova.query.user.query_handlers

import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.user.Role
import com.esgi.nova.core_api.user.queries.FindPaginatedUserAdminEditsByUsernameQuery
import com.esgi.nova.core_api.user.views.UserAdminEditView
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import com.esgi.nova.query.user.UserRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedUserAdminEditsByUsernameHandler(private val userRepository: UserRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedUserAdminEditsByUsernameQuery): PageBase<UserAdminEditView> {
        val adminUsers = userRepository.getAllByRole(Role.ADMIN)
        return userRepository.getAllByUsernameStartingWith(query.username, query.toPageable())
            .map { it.toUserAdminEdit(!(it.role == Role.ADMIN && adminUsers.size == 1)) }.toStaticPage(query)
    }
}