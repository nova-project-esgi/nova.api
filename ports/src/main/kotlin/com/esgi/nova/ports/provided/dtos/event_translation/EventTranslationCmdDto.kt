package com.esgi.nova.ports.provided.dtos.event_translation

import java.util.*

open class EventTranslationCmdDto(
    override var title: String,
    override var description: String,
    var eventId: UUID
) : IEventTranslation {
}