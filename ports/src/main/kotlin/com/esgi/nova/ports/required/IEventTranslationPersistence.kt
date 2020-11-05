package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import java.util.*

interface IEventTranslationPersistence : IGetAll<EventTranslationDto>,
    ICreate<EventTranslationCmdDto<UUID>, EventTranslationDto>,
    IGetAllTotal<EventTranslationDto>,
    IGetOne<EventTranslationKey<UUID>, EventTranslationDto>,
    IUpdateOne<EventTranslationCmdDto<UUID>, EventTranslationKey<UUID>, EventTranslationDto> {
    fun getTotalByLanguages(pagination: IPagination, languageIds: List<UUID>): ITotalCollection<EventTranslationDto>
    fun getAllByEventIdAndLanguageId(eventId: UUID, languageId: UUID): List<EventTranslationDto>
    fun getOneDefault(eventId: UUID): EventTranslationDto?
}