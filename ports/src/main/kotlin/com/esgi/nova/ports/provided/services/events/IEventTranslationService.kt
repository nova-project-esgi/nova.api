package com.esgi.nova.ports.provided.services.events

import com.esgi.nova.ports.provided.dtos.event_translation.*
import com.esgi.nova.ports.provided.services.ICrudService
import com.esgi.nova.ports.provided.services.ITranslationService
import java.util.*

interface IEventTranslationService : ICrudService<EventTranslationKey<UUID>, EventTranslationCmdDto<UUID>, EventTranslationDto>,
        ITranslationService<EventTranslationKey<UUID>, EventTranslationDto> {
    fun getOneOrDefault(id: EventTranslationKey<UUID>): EventTranslationDto?
}