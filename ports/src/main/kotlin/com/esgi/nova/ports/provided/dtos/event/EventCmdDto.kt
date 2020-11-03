package com.esgi.nova.ports.provided.dtos.event


data class EventCmdDto(
    override var isDaily: Boolean,
    override var isActive: Boolean,
) : IEvent
