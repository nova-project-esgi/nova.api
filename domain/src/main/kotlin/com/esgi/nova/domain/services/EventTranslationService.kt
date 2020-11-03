package com.esgi.nova.domain.services

import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationLanguageCodesCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationLanguageIdCmdDto
import com.esgi.nova.ports.provided.services.IEventTranslationService
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.required.IEventTranslationPersistence
import com.google.inject.Inject
import java.util.*

class EventTranslationService @Inject constructor(
    private val eventTranslationPersistence: IEventTranslationPersistence,
    private val languageService: ILanguageService
) :
    IEventTranslationService {
    override fun getOneByEventIdAndLanguageCodes(eventId: UUID, codes: String): EventTranslationDto? {
        languageService.getOneByCodes(codes)?.let { l ->
            return eventTranslationPersistence.getOne(EventTranslationKey(eventId, l.id))
        }
        return null
    }

    override fun createOneWithCodes(eventCmd: EventTranslationLanguageCodesCmdDto): EventTranslationDto? {
        languageService.getOneByCodes(eventCmd.languageCodes)?.let { l ->
            return create(
                EventTranslationLanguageIdCmdDto(
                    eventCmd.title,
                    eventCmd.description,
                    l.id,
                    eventCmd.eventId
                )
            )
        }
        return null
    }

    override fun getAll(): Collection<EventTranslationDto> = eventTranslationPersistence.getAll()
    override fun create(element: EventTranslationLanguageIdCmdDto): EventTranslationDto? =
        eventTranslationPersistence.create(element)

    override fun getOne(id: EventTranslationKey): EventTranslationDto? = eventTranslationPersistence.getOne(id)

    override fun getPage(pagination: IPagination): IPage<EventTranslationDto> =
        eventTranslationPersistence.getAllTotal(pagination).toStaticPage(pagination)

    override fun getOneOrDefault(id: EventTranslationKey): EventTranslationDto? {
        return eventTranslationPersistence.getOne(id)
            ?: eventTranslationPersistence.getOneDefault(id.eventId)
    }
}