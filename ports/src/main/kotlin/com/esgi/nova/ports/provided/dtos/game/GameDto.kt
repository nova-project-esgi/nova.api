package com.esgi.nova.ports.provided.dtos.game

import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.user.UserDto
import com.github.pozo.KotlinBuilder
import java.time.LocalDateTime
import java.util.*

class GameDto(var id: UUID, var user: UserDto, var startDate: LocalDateTime, var score: Int, var events: List<EventDto>)
