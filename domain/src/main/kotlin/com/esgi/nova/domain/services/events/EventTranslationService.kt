package com.esgi.nova.domain.services.events

import com.esgi.nova.domain.services.service.BaseService
import com.esgi.nova.ports.provided.dtos.event_translation.*
import com.esgi.nova.ports.provided.services.events.IEventTranslationService
import com.esgi.nova.ports.required.IEventTranslationPersistence
import com.google.inject.Inject
import java.util.*

class EventTranslationService @Inject constructor(
    private val eventTranslationPersistence: IEventTranslationPersistence,
) :BaseService<EventTranslationKey<UUID>, EventTranslationCmdDto<UUID>, EventTranslationDto>(eventTranslationPersistence),
        IEventTranslationService {


    override fun getOneOrDefault(id: EventTranslationKey<UUID>): EventTranslationDto? {
        return eventTranslationPersistence.getOne(id)
            ?: eventTranslationPersistence.getOneDefault(id.entityId)
    }
}