package com.esgi.nova.query.user.event_handlers

import com.esgi.nova.core_api.user.events.UserCreatedEvent
import com.esgi.nova.query.user.User
import com.esgi.nova.query.user.UserRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnUserCreatedEventHandler constructor(val userRepository: UserRepository) {
    @EventHandler
    fun on(event: UserCreatedEvent) {
        userRepository.saveAndFlush(User(event.userId.toUUID(), event.username, event.password, event.email, event.role, listOf()))
    }
}