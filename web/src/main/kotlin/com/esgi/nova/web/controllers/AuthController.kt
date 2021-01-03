package com.esgi.nova.web.controllers

import com.esgi.nova.application.uses_cases.users.models.ConnectedUser
import com.esgi.nova.application.uses_cases.users.models.UserLogin
import com.esgi.nova.application.uses_cases.users.models.UserEdition
import com.esgi.nova.application.uses_cases.users.UsersUseCases
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("auth")
open class AuthController constructor(private val usersUseCases: UsersUseCases) {


    @PostMapping("register")
    open fun register(@RequestBody user: UserEdition): ResponseEntity<ConnectedUser> {
        return ResponseEntity.ok(usersUseCases.register(user))
    }


    @PostMapping("login")
    fun login(@RequestBody credentials: UserLogin): ResponseEntity<ConnectedUser> {
        return ResponseEntity.ok(usersUseCases.login(credentials))
    }

    @GetMapping
    open fun getByToken(@RequestParam token: String): ResponseEntity<ConnectedUser> {
        return ResponseEntity.ok(usersUseCases.getByToken(token))
//        return ResponseEntity(HttpStatus.NOT_FOUND)
    }
}