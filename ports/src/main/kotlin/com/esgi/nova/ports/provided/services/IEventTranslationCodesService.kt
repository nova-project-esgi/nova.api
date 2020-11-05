package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey

interface IEventTranslationCodesService : IGetOne<EventTranslationKey<String>, EventTranslationDto>,
    ICreate<EventTranslationCmdDto<String>, EventTranslationDto>,
    IUpdateOne<EventTranslationCmdDto<String>, EventTranslationKey<String>, EventTranslationDto> {


}