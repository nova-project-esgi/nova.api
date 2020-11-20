package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import java.util.*

interface IEventTranslationPersistence :
        ICrudPersistence<EventTranslationKey<UUID>, EventTranslationCmdDto<UUID>, EventTranslationDto>,
        IGetAllTotalById<UUID,  EventTranslationDto>,
        IGetAllTotalByIds<UUID,  EventTranslationDto>{
    fun getOneDefault(eventId: UUID): EventTranslationDto?
}