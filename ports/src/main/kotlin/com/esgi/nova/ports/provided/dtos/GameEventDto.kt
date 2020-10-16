package com.esgi.nova.ports.provided.dtos

import com.github.pozo.KotlinBuilder
import java.time.LocalDateTime

@KotlinBuilder
data class GameEventDto(var game: GameDto, var event: EventDto, var linkTime: LocalDateTime)
