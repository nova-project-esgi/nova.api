package com.esgi.nova.domain.services.events

import com.esgi.nova.domain.services.translation_service.BaseTranslationCodesService
import com.esgi.nova.domain.services.translation_service.ICodeIdToUuidId
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.provided.services.events.IEventTranslationCodesService
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
    override var codeMapper: ICodeIdToUuidId<EventTranslationKey<String>, EventTranslationKey<UUID>> = this

    override fun transformInput(
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
    override fun transformId(codeId: EventTranslationKey<String>, languageId: UUID): EventTranslationKey<UUID> {
        return EventTranslationKey(codeId.eventId, languageId)
    }


}