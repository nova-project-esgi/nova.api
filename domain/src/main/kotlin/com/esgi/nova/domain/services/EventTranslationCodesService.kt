package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.provided.services.IEventTranslationCodesService
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.required.IEventTranslationPersistence
import com.google.inject.Inject

class EventTranslationCodesService @Inject constructor(
    private val eventTranslationPersistence: IEventTranslationPersistence,
    private val languageService: ILanguageService
) : IEventTranslationCodesService {
    override fun getOne(id: EventTranslationKey<String>): EventTranslationDto? {
        languageService.getOneByCodes(id.language)?.let { l ->
            return eventTranslationPersistence.getOne(EventTranslationKey(id.eventId, l.id))
        }
        return null
    }

    override fun create(element: EventTranslationCmdDto<String>): EventTranslationDto? {
        languageService.getOneByCodes(element.language)?.let { l ->
            return eventTranslationPersistence.create(
                EventTranslationCmdDto(
                    element.title,
                    element.description,
                    l.id,
                    element.eventId
                )
            )
        }
        return null
    }

    override fun updateOne(
        element: EventTranslationCmdDto<String>,
        id: EventTranslationKey<String>
    ): EventTranslationDto? {
        languageService.getOneByCodes(element.language)?.let { l ->
            return eventTranslationPersistence.updateOne(
                EventTranslationCmdDto(
                    element.title,
                    element.description,
                    l.id,
                    element.eventId
                ),
                EventTranslationKey(id.eventId, l.id)
            )
        }
        return null
    }


}