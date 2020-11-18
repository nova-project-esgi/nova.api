package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.commands.TranslatedChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.choice.commands.TranslatedChoiceWithResourcesCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.translated_choice_detailed.TranslatedResourceDetailedDto
import com.esgi.nova.ports.provided.dtos.event.translated_event_detailed.TranslatedChoiceDetailedDto
import java.util.*

interface ITranslatedChoiceService {
    fun createTranslatedChoiceAndAttachResources(
        element: TranslatedChoiceWithResourcesCmdDto
    ): TranslatedChoiceDto?

    fun createTranslatedChoice(translatedChoice: TranslatedChoiceCmdDto): TranslatedChoiceDto?
    fun getTranslatedChoicesPage(
        pagination: IPagination,
        codes: String,
        includeEvent: Boolean,
        includeResources: Boolean
    ): IPage<TranslatedChoiceDto>

    fun getTranslatedChoice(
        id: UUID,
        codes: String,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedChoiceDto?

    fun getTranslatedChoice(
        id: UUID,
        languageId: UUID,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedChoiceDto?

    fun getTranslatedChoicesByIdsAndLanguageId(
        choiceIds: List<UUID>,
        languageId: UUID,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguages: Boolean
    ): List<TranslatedChoiceDto>

    fun getTranslatedChoicesByEventIdAndLanguageId(
        eventId: UUID,
        languageId: UUID,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguage: Boolean
    ): List<TranslatedChoiceDto>

    fun getTranslatedChoicesByResourceIdAndLanguageId(
        resourceId: UUID,
        languageId: UUID,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguage: Boolean
    ): List<TranslatedChoiceDto>

    fun getTranslatedChoiceDetailed(
        id: UUID,
        codes: String,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedChoiceDetailedDto?

    fun convertTranslatedChoiceToTranslatedChoiceDetailed(translatedChoice: TranslatedChoiceDto): TranslatedChoiceDetailedDto?

    fun setChangeValueToTranslatedResourceDetailed(resources: List<TranslatedResourceDetailedDto>, choiceId: UUID)

}