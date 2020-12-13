package com.esgi.nova.web.controllers

import com.esgi.nova.application.uses_cases.users.UsersUseCases
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("users")
open class UserController(private val usersUseCases: UsersUseCases) {

    @Secured("ROLE_USER")
    @DeleteMapping("{id}")
    open fun delete(@PathVariable(value = "id") id: UUID) {
        usersUseCases.delete(id)
    }



}

