package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceDto
import com.esgi.nova.ports.provided.dtos.resource.translated_resource_detailed.TranslatedResourceDetailedDto
import java.util.*

interface ITranslatedResourceService {
    fun getTranslatedResource(
        id: UUID,
        codes: String,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedResourceDto?

    fun getTranslatedResourcesByChoiceIdAndLanguageCodes(
        choiceId: UUID,
        codes: String,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): Collection<TranslatedResourceDto>

    fun getTranslatedResource(
        id: UUID,
        languageId: UUID,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedResourceDto?

    fun getTranslatedResourcesPage(
        pagination: IPagination,
        codes: String,
        includeChoices: Boolean
    ): IPage<TranslatedResourceDto>

    fun createTranslatedResource(
        translatedResource: TranslatedResourceCmdDto,
        codes: String
    ): TranslatedResourceDto?

    fun getTranslatedResourcesByChoiceIdAndLanguageId(
        choiceId: UUID,
        languageId: UUID,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): Collection<TranslatedResourceDto>

    fun getTranslatedResourceDetailedDto(
        id: UUID,
        codes: String,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedResourceDetailedDto?

    fun convertTranslatedResourceToTranslatedResourceDetailed(translatedResource: TranslatedResourceDto): TranslatedResourceDetailedDto?
}