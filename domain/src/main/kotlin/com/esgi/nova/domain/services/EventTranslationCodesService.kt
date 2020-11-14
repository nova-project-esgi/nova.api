package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.provided.services.IEventTranslationCodesService
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.required.IEventTranslationPersistence
import com.google.inject.Inject
import java.util.*

class EventTranslationCodesService @Inject constructor(
    override val persistence: IEventTranslationPersistence,
    languageService: ILanguageService
): BaseTranslationCodesService<
        EventTranslationKey<String>,
        EventTranslationKey<UUID>,
        EventTranslationCmdDto<String>,
        EventTranslationCmdDto<UUID>,
        EventTranslationDto>(
    languageService,
    persistence
), IEventTranslationCodesService {

    override fun codeInputToUuidInput(
        codesInput: EventTranslationCmdDto<String>,
        languageId: UUID
    ): EventTranslationCmdDto<UUID> {
        return EventTranslationCmdDto(
            codesInput.title,
            codesInput.description,
            languageId,
            codesInput.eventId
        )
    }
    override fun codeIdToUuidId(codeId: EventTranslationKey<String>, languageId: UUID): EventTranslationKey<UUID> {
        return EventTranslationKey(codeId.eventId, languageId)
    }


}