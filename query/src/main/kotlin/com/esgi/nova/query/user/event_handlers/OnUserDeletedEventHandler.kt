package com.esgi.nova.query.user.event_handlers

import com.esgi.nova.core_api.user.events.UserDeletedEvent
import com.esgi.nova.query.user.UserRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnUserDeletedEventHandler constructor(private val userRepository: UserRepository) {
    @EventHandler
    fun on(event: UserDeletedEvent) {
        userRepository.deleteById(event.userId.toUUID())
    }
}
