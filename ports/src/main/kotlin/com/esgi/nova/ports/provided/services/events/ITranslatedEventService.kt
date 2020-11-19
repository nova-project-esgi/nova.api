package com.esgi.nova.ports.provided.services.events

import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventCmdDto
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventDto
import com.esgi.nova.ports.provided.dtos.event.translated_event_detailed.TranslatedEventDetailedDto
import java.util.*

interface ITranslatedEventService {
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

    fun createTranslatedEvent(translatedEvent: TranslatedEventCmdDto): TranslatedEventDto?
    fun convertTranslatedEventToTranslatedEventDetailed(translatedEventDto: TranslatedEventDto): TranslatedEventDetailedDto
    fun getTranslatedEventDetailed(
        id: UUID,
        codes: String,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedEventDetailedDto?
}