package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import java.util.*

interface IEventTranslationPersistence :
    ITranslationPersistence<EventTranslationKey<UUID>, EventTranslationCmdDto<UUID>, EventTranslationDto> {
    fun getTotalByLanguages(pagination: IPagination, languageIds: List<UUID>): ITotalCollection<EventTranslationDto>
    fun getAllByEventIdAndLanguageId(eventId: UUID, languageId: UUID): Collection<EventTranslationDto>
    fun getOneDefault(eventId: UUID): EventTranslationDto?
}