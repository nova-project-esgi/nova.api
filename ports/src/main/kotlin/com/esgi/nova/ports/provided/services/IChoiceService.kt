package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.*
import com.esgi.nova.ports.provided.dtos.choice.translated_choice_detailed.TranslatedResourceDetailedDto
import com.esgi.nova.ports.provided.dtos.event.translated_event_detailed.TranslatedChoiceDetailedDto
import java.util.*

interface IChoiceService : IGetAll<ChoiceDto>, IGetOne<UUID, ChoiceDto>, ICreate<ChoiceCmdDto, ChoiceDto>,
    IGetPage<ChoiceDto> {
    fun createChoiceAndAttachResources(element: ChoiceWithResourcesCmdDto): ChoiceDto?
    fun createTranslatedChoiceAndAttachResources(
        element: TranslatedChoiceWithResourcesCmdDto,
        codes: String
    ): TranslatedChoiceDto?

    fun createTranslatedChoice(translatedChoice: TranslatedChoiceCmdDto, codes: String): TranslatedChoiceDto?
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