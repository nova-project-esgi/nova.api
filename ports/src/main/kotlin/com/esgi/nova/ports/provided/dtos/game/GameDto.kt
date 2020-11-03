package com.esgi.nova.ports.provided.dtos.game

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.user.UserDto
import java.time.LocalDateTime
import java.util.*

class GameDto(
    override var id: UUID,
    var user: UserDto,
    override var startDate: LocalDateTime,
    override var score: Int,
    var events: List<EventDto>
) : IId<UUID>, IGame
