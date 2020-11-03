package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventCmdDto
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventDto
import com.esgi.nova.ports.provided.dtos.event.translated_event_detailed.TranslatedEventDetailedDto
import java.util.*

interface IEventService : IGetAll<EventDto>, ICreate<EventCmdDto, EventDto>, IGetOne<UUID, EventDto>,
    IGetPage<EventDto> {
    fun getTranslatedEvent(
        id: UUID,
        languageId: UUID,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedEventDto?

    fun getTranslatedEvent(
        id: UUID,
        codes: String,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedEventDto?

    fun getTranslatedEventsPage(
        pagination: IPagination,
        codes: String,
        includeChoices: Boolean
    ): IPage<TranslatedEventDto>

    fun createTranslatedEvent(translatedEvent: TranslatedEventCmdDto, codes: String): TranslatedEventDto?
    fun convertTranslatedEventToTranslatedEventDetailed(translatedEventDto: TranslatedEventDto): TranslatedEventDetailedDto
    fun getTranslatedEventDetailed(
        id: UUID,
        codes: String,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedEventDetailedDto?
}