package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.EventDto

interface IEventService {
    fun getAll(): List<EventDto>
    fun create(event: EventDto): EventDto
}