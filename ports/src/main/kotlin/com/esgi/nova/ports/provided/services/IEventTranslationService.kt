package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.dtos.event_translation.*
import java.util.*

interface IEventTranslationService : ICrudService<EventTranslationKey<UUID>, EventTranslationCmdDto<UUID>, EventTranslationDto> {
    fun getOneOrDefault(id: EventTranslationKey<UUID>): EventTranslationDto?
}