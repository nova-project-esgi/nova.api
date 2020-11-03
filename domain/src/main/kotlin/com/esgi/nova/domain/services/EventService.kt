package com.esgi.nova.domain.services

import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.event.translated_event_detailed.TranslatedChoiceDetailedDto
import com.esgi.nova.ports.provided.dtos.event.*
import com.esgi.nova.ports.provided.dtos.event.translated_event_detailed.TranslatedEventDetailedDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationLanguageIdCmdDto
import com.esgi.nova.ports.provided.services.*
import com.esgi.nova.ports.required.IEventPersistence
import com.esgi.nova.ports.required.IEventTranslationPersistence
import com.google.inject.Inject
import java.util.*

class EventService @Inject constructor(
    private val eventPersistence: IEventPersistence,
    private val eventTranslationPersistence: IEventTranslationPersistence,
    private val eventTranslationService: IEventTranslationService,
    private val languageService: ILanguageService,
    private val choiceService: IChoiceService,
    private val choiceResourceService: IChoiceResourceService
) : IEventService {

    private fun handleUseDefaultForOne(id: EventTranslationKey, useDefault: Boolean = false): EventTranslationDto? {
        return if (useDefault) {
            eventTranslationService.getOneOrDefault(id)
        } else {
            eventTranslationService.getOne(id)
        }
    }

    override fun getTranslatedEvent(
        id: UUID,
        languageId: UUID,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedEventDto? {
        handleUseDefaultForOne(EventTranslationKey(id, languageId))?.let { eventTranslation ->
            return convertEventTranslationToTranslatedEvent(eventTranslation, languageId, includeChoices)
        }
        return null
    }


    override fun getTranslatedEvent(
        id: UUID,
        codes: String,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedEventDto? {
        languageService.getOneByCodes(codes)?.let { language ->
            handleUseDefaultForOne(EventTranslationKey(id, language.id))?.let { eventTranslation ->
                return convertEventTranslationToTranslatedEvent(eventTranslation, language.id, includeChoices)
            }
        }
        return null
    }

    override fun getTranslatedEventDetailed(
        id: UUID,
        codes: String,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedEventDetailedDto? {
        getTranslatedEvent(id, codes, includeChoices, useDefaultLanguage)?.let { translatedEvent ->
            return convertTranslatedEventToTranslatedEventDetailed(translatedEvent)
        }
        return null
    }

    override fun convertTranslatedEventToTranslatedEventDetailed(translatedEventDto: TranslatedEventDto): TranslatedEventDetailedDto {
        val translatedEventDetailed = TranslatedEventDetailedDto(translatedEventDto)
        translatedEventDetailed.choices = translatedEventDto.choices.map { choice ->
            TranslatedChoiceDetailedDto(choice).apply {
                choiceService.setChangeValueToTranslatedResourceDetailed(resources, id)
            }
        }
        return translatedEventDetailed
    }

    override fun getTranslatedEventsPage(
        pagination: IPagination,
        codes: String,
        includeChoices: Boolean
    ): IPage<TranslatedEventDto> {
        val translations = eventTranslationPersistence.getTotalByLanguages(
            pagination,
            languageService.getAllByCodes(codes).map { l -> l.id })
        return translations.map { t -> convertEventTranslationToTranslatedEvent(t, t.language.id, includeChoices) }
            .toStaticPage(pagination, translations.total.toInt())
    }

    override fun createTranslatedEvent(translatedEvent: TranslatedEventCmdDto, codes: String): TranslatedEventDto? {
        languageService.getOneByCodes(codes)?.let { language ->
            eventPersistence.create(EventCmdDto(translatedEvent.isDaily, translatedEvent.isActive))?.let { event ->
                eventTranslationPersistence.create(
                    EventTranslationLanguageIdCmdDto(
                        translatedEvent.title,
                        translatedEvent.description,
                        language.id,
                        event.id
                    )
                )?.let { eventTranslation ->
                    return convertEventTranslationToTranslatedEvent(
                        eventTranslation, language.id,
                        includeChoices = false
                    )
                }
            }
        }
        return null
    }

    private fun convertEventTranslationToTranslatedEvent(
        eventTranslation: EventTranslationDto,
        languageId: UUID,
        includeChoices: Boolean = true
    ): TranslatedEventDto {
        var translatedChoices: Collection<TranslatedChoiceDto>? = null
        if (includeChoices) {
            translatedChoices = choiceService.getTranslatedChoicesByEventIdAndLanguageId(
                eventTranslation.event.id, languageId,
                includeEvent = false,
                includeResources = true,
                useDefaultLanguage = true
            )
        }
        return eventTranslation.toTranslatedEventDto(translatedChoices?.toList())
    }

    override fun getAll() = eventPersistence.getAll()
    override fun create(element: EventCmdDto) = eventPersistence.create(element)
    override fun getOne(id: UUID): EventDto? = eventPersistence.getOne(id)
    override fun getPage(pagination: IPagination): IPage<EventDto> =
        eventPersistence.getAllTotal(pagination).toStaticPage(pagination)

}