package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.EventDto

interface IEventPersistence {
    fun getAll(): List<EventDto>
    fun create(event: EventDto): EventDto
}