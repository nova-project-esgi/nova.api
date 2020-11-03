package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationLanguageCodesCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationLanguageIdCmdDto
import java.util.*

interface IEventTranslationService : IGetAll<EventTranslationDto>,
    ICreate<EventTranslationLanguageIdCmdDto, EventTranslationDto>,
    IGetOne<EventTranslationKey, EventTranslationDto>,
    IGetPage<EventTranslationDto> {
    fun getOneByEventIdAndLanguageCodes(eventId: UUID, codes: String): EventTranslationDto?
    fun createOneWithCodes(eventCmd: EventTranslationLanguageCodesCmdDto): EventTranslationDto?
    fun getOneOrDefault(id: EventTranslationKey): EventTranslationDto?
}