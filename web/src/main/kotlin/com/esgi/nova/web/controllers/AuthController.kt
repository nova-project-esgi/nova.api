package com.esgi.nova.web.controllers

import com.esgi.nova.application.services.users.UsersService
import com.esgi.nova.application.services.users.models.ConnectedUser
import com.esgi.nova.application.services.users.models.UserEdition
import com.esgi.nova.application.services.users.models.UserLogin
import com.esgi.nova.core.domain.users.exceptions.UserAlreadyExistsException
import com.esgi.nova.core.domain.users.exceptions.UserNotFoundByUsernameAndPasswordException
import com.esgi.nova.core_api.user.exceptions.UserNotFoundByTokenException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("auth")
open class AuthController constructor(private val usersService: UsersService) {


    @PostMapping("register")
    open fun register(@RequestBody user: UserEdition): ResponseEntity<ConnectedUser> {
        return try{
            ResponseEntity.ok(usersService.register(user))
        }catch (e: UserAlreadyExistsException){
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }


    @PostMapping("login")
    fun login(@RequestBody credentials: UserLogin): ResponseEntity<ConnectedUser> {
        return try {
            ResponseEntity.ok(usersService.login(credentials))
        } catch (e: UserNotFoundByUsernameAndPasswordException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    open fun getByToken(@RequestParam token: String): ResponseEntity<ConnectedUser> {
        return try {
            ResponseEntity.ok(usersService.getByToken(token))
        } catch (e: UserNotFoundByTokenException) {
            ResponseEntity.notFound().build()
        }
    }
}