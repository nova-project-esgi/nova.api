package com.esgi.nova.web.security

import com.esgi.nova.application.uses_cases.users.UsersUseCases
import com.esgi.nova.core_api.user.exceptions.UserNotFoundByUsernameException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class AuthService(private val usersUseCases: UsersUseCases) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        try {
            usersUseCases.getCredentialByUsername(username)
                .let { credential -> return User(credential.username, credential.password, emptyList()) }
        } catch (e: UserNotFoundByUsernameException) {
            throw UsernameNotFoundException(username)
        }
    }

}