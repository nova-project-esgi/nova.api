package com.esgi.nova.query.user.event_handlers

import com.esgi.nova.core_api.user.events.UserUpdatedEvent
import com.esgi.nova.query.user.UserRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class OnUserUpdated constructor(private val userRepository: UserRepository) {
    @EventHandler
    fun on(event: UserUpdatedEvent) {
        userRepository.findByIdOrNull(event.userId.toUUID())?.let { user ->
            user.role = event.role
            user.email = event.email
            user.password = event.password
            user.username = event.username
            userRepository.saveAndFlush(user)
        }
    }
}