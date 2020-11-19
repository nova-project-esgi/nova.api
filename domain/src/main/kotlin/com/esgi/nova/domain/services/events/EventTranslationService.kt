package com.esgi.nova.domain.services.events

import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event_translation.*
import com.esgi.nova.ports.provided.services.events.IEventTranslationService
import com.esgi.nova.ports.required.IEventTranslationPersistence
import com.google.inject.Inject
import java.util.*

class EventTranslationService @Inject constructor(
    private val eventTranslationPersistence: IEventTranslationPersistence,
) :
        IEventTranslationService {


    override fun getAll(): Collection<EventTranslationDto> = eventTranslationPersistence.getAll()
    override fun create(element: EventTranslationCmdDto<UUID>): EventTranslationDto? =
        eventTranslationPersistence.create(element)

    override fun getOne(id: EventTranslationKey<UUID>): EventTranslationDto? = eventTranslationPersistence.getOne(id)

    override fun getPage(pagination: IPagination): IPage<EventTranslationDto> =
        eventTranslationPersistence.getAllTotal(pagination).toStaticPage(pagination)

    override fun updateOne(element: EventTranslationCmdDto<UUID>, id: EventTranslationKey<UUID>): EventTranslationDto? = eventTranslationPersistence.updateOne(
        element,
        id
    )

    override fun getOneOrDefault(id: EventTranslationKey<UUID>): EventTranslationDto? {
        return eventTranslationPersistence.getOne(id)
            ?: eventTranslationPersistence.getOneDefault(id.eventId)
    }
}