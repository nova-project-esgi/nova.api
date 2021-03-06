package com.esgi.nova.web.controllers

import com.esgi.nova.application.pagination.PageMetadata
import com.esgi.nova.application.pagination.PaginationDefault
import com.esgi.nova.application.services.users.UsersService
import com.esgi.nova.application.services.users.models.UserRole
import com.esgi.nova.core_api.user.views.UserAdminEditView
import com.esgi.nova.core_api.user.views.UserUsername
import com.esgi.nova.web.content_negociation.CustomMediaType
import com.esgi.nova.web.extensions.toPageMetadata
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("users")
open class UserController(private val usersService: UsersService) {

    @Secured("ROLE_USER")
    @DeleteMapping("{id}")
    open fun delete(@PathVariable(value = "id") id: UUID) {
        usersService.delete(id)
    }


    @GetMapping(produces = [CustomMediaType.Application.UserUsername])
    open fun getPaginatedResourceNameByUsername(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "username") username: String
    ): PageMetadata<UserUsername> {
        return usersService.getPaginatedUserUsernamesByUsername(
            page,
            size,
            username
        ).toPageMetadata()
    }


    @GetMapping(produces = [CustomMediaType.Application.UserAdminEdit])
    open fun getPaginatedUserAdminEditByUsername(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "username") username: String
    ): PageMetadata<UserAdminEditView> {
        return usersService.getPaginatedUserUserAdminEditsByUsername(
            page,
            size,
            username
        ).toPageMetadata()
    }

    @PutMapping("{id}", consumes = [CustomMediaType.Application.UserRole])
    open fun updateUserRole(
        @PathVariable id: UUID, @RequestBody userRole: UserRole
    ): ResponseEntity<Any> {
        usersService.updateUserRole(
            id,
            userRole
        )
        return ResponseEntity.noContent().build()
    }


}

