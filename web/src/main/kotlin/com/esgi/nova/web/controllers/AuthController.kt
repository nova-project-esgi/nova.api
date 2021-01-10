package com.esgi.nova.web.controllers

import com.esgi.nova.application.services.users.UsersService
import com.esgi.nova.application.services.users.models.ConnectedUser
import com.esgi.nova.application.services.users.models.UserEdition
import com.esgi.nova.application.services.users.models.UserLogin
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("auth")
open class AuthController constructor(private val usersService: UsersService) {


    @PostMapping("register")
    open fun register(@RequestBody user: UserEdition): ResponseEntity<ConnectedUser> {
        return ResponseEntity.ok(usersService.register(user))
    }


    @PostMapping("login")
    fun login(@RequestBody credentials: UserLogin): ResponseEntity<ConnectedUser> {
        return ResponseEntity.ok(usersService.login(credentials))
    }

    @GetMapping
    open fun getByToken(@RequestParam token: String): ResponseEntity<ConnectedUser> {
        return ResponseEntity.ok(usersService.getByToken(token))
//        return ResponseEntity(HttpStatus.NOT_FOUND)
    }
}