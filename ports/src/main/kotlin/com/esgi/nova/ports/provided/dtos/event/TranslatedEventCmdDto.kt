package com.esgi.nova.ports.provided.dtos.event

import com.esgi.nova.ports.provided.dtos.event_translation.IEventTranslation

class TranslatedEventCmdDto(
    override var title: String,
    override var description: String,
    override var isDaily: Boolean,
    override var isActive: Boolean,
) : IEventTranslation, IEvent {

}