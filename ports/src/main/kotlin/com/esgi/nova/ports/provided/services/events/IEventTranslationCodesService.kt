package com.esgi.nova.ports.provided.services.events

import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.provided.services.ICrudService
import com.esgi.nova.ports.provided.services.ITranslationService

interface IEventTranslationCodesService : ICrudService<EventTranslationKey<String>, EventTranslationCmdDto<String>, EventTranslationDto>, ITranslationService<String, EventTranslationDto> {
}