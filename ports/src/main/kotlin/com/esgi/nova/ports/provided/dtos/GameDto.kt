package com.esgi.nova.ports.provided.dtos

import com.github.pozo.KotlinBuilder
import java.time.LocalDateTime

@KotlinBuilder
data class GameDto(var user: UserDto, var startDate: LocalDateTime, var score: Int, var events: Iterable<EventDto>)
