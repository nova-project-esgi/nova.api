package com.esgi.nova.ports.provided.dtos.game_event

import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.github.pozo.KotlinBuilder
import java.time.LocalDateTime

class GameEventDto(var game: GameDto, var event: EventDto, var linkTime: LocalDateTime)
