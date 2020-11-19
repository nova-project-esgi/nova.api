package com.esgi.nova.ports.provided.services.events

import com.esgi.nova.ports.provided.dtos.event_translation.*
import com.esgi.nova.ports.provided.services.ICrudService
import java.util.*

interface IEventTranslationService : ICrudService<EventTranslationKey<UUID>, EventTranslationCmdDto<UUID>, EventTranslationDto> {
    fun getOneOrDefault(id: EventTranslationKey<UUID>): EventTranslationDto?
}