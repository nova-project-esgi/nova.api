package com.esgi.nova.ports.provided.dtos.event_translation

import com.esgi.nova.ports.provided.dtos.ITranslation
import java.util.*

open class EventTranslationCmdDto<T>(
    override var title: String,
    override var description: String,
    var eventId: UUID,
    override var language: T
) : IEventTranslation, ITranslation<T> {
}