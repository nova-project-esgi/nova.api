package com.esgi.nova.domain.services

import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.queries.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceDto
import com.esgi.nova.ports.provided.dtos.resource.translated_resource_detailed.TranslatedResourceDetailedDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.services.*
import com.esgi.nova.ports.required.IResourcePersistence
import com.esgi.nova.ports.required.IResourceTranslationPersistence
import com.google.inject.Inject
import java.util.*

class TranslatedResourceService @Inject constructor(
    private val resourcePersistence: IResourcePersistence,
    private val languageService: ILanguageService,
    private val resourceTranslationPersistence: IResourceTranslationPersistence,
    private val resourceTranslationService: IResourceTranslationService,
    private val choiceResourceService: IChoiceResourceService,
    private val translatedChoiceService: ITranslatedChoiceService
) : ITranslatedResourceService {
    override fun getTranslatedResource(
        id: UUID,
        codes: String,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedResourceDto? {
        languageService.getOneByCodes(codes)?.let { language ->
            handleUseDefaultForOne(ResourceTranslationKey(id, language.id), useDefaultLanguage)
                ?.let { resourceTranslationDto ->
                    return convertResourceTranslationToTranslatedResource(
                        resourceTranslationDto, language.id,
                        includeChoices
                    )
                }
        }
        return null
    }

    override fun getTranslatedResourceDetailedDto(
        id: UUID,
        codes: String,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedResourceDetailedDto? {
        getTranslatedResource(id, codes, includeChoices, useDefaultLanguage)?.let { translatedResource ->
            return convertTranslatedResourceToTranslatedResourceDetailed(translatedResource)
        }
        return null
    }

    override fun convertTranslatedResourceToTranslatedResourceDetailed(translatedResource: TranslatedResourceDto): TranslatedResourceDetailedDto? {
        val translatedResourceDetailed = TranslatedResourceDetailedDto(translatedResource)
        choiceResourceService.getAllByResourceId(translatedResourceDetailed.id).forEach { choiceResource ->
            translatedResourceDetailed.choices.forEach() { choice ->
                if (choice.id == choiceResource.choice.id) {
                    choice.changeValue = choiceResource.changeValue
                }
            }
        }
        return translatedResourceDetailed

    }

    private fun handleUseDefaultForOne(
        id: ResourceTranslationKey<UUID>,
        useDefault: Boolean = false
    ): ResourceTranslationDto? {
        return if (useDefault) {
            resourceTranslationService.getOneOrDefault(id)
        } else {
            resourceTranslationService.getOne(id)
        }
    }

    override fun getTranslatedResource(
        id: UUID,
        languageId: UUID,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedResourceDto? {
        handleUseDefaultForOne(ResourceTranslationKey(id, languageId), useDefaultLanguage)
            ?.let { resourceTranslationDto ->
                return convertResourceTranslationToTranslatedResource(
                    resourceTranslationDto, languageId,
                    includeChoices
                )
            }
        return null
    }

    override fun getTranslatedResourcesByChoiceIdAndLanguageCodes(
        choiceId: UUID,
        codes: String,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): Collection<TranslatedResourceDto> {
        languageService.getOneByCodes(codes)
            ?.let { language ->
                getTranslatedResourcesByChoiceIdAndLanguageId(choiceId, language.id, includeChoices, useDefaultLanguage)
            }
        return listOf()
    }

    override fun getTranslatedResourcesByChoiceIdAndLanguageId(
        choiceId: UUID,
        languageId: UUID,
        includeChoices: Boolean,
        useDefaultLanguage: Boolean
    ): Collection<TranslatedResourceDto> {
        val translationDtos: Collection<ResourceTranslationDto> = if (useDefaultLanguage) {
            resourceTranslationService.getAllByChoiceIdAndLanguageIdWithDefaults(choiceId, languageId)
        } else {
            resourceTranslationPersistence.getAllByChoiceIdAndLanguageId(choiceId, languageId)
        }
        return translationDtos
            .map { resourceTranslation ->
                convertResourceTranslationToTranslatedResource(
                    resourceTranslation, languageId,
                    includeChoices
                )
            }
    }


    override fun getTranslatedResourcesPage(
        pagination: IPagination,
        codes: String,
        includeChoices: Boolean
    ): IPage<TranslatedResourceDto> {
        val translations = resourceTranslationPersistence.getTotalByLanguages(
            pagination,
            languageService.getAllByCodes(codes).map { l -> l.id })
        return translations.map { t ->
            convertResourceTranslationToTranslatedResource(
                t,
                t.language.id,
                includeChoices
            )
        }
            .toStaticPage(pagination, translations.total.toInt())
    }

    override fun createTranslatedResource(
        translatedResource: TranslatedResourceCmdDto
    ): TranslatedResourceDto? {
        languageService.getOneByCodes(translatedResource.language)?.let { language ->
            resourcePersistence.create(ResourceCmdDto())?.let { resource ->
                resourceTranslationPersistence.create(
                    ResourceTranslationCmdDto(
                        translatedResource.name,
                        resource.id,
                        language.id
                    )
                )?.let { resourceTranslationDto ->
                    return convertResourceTranslationToTranslatedResource(resourceTranslationDto, language.id)
                }
            }
        }
        return null
    }

    private fun convertResourceTranslationToTranslatedResource(
        resourceTranslation: ResourceTranslationDto,
        languageId: UUID,
        includeChoices: Boolean = false
    ): TranslatedResourceDto {
        var choices: Collection<TranslatedChoiceDto>? = null
        if (includeChoices) {
            choices = translatedChoiceService.getTranslatedChoicesByResourceIdAndLanguageId(
                resourceTranslation.resource.id,
                languageId,
                includeEvent = true,
                includeResources = false,
                useDefaultLanguage = true
            )
        }
        return resourceTranslation.toTranslatedResourceDto(choices?.toList() ?: listOf())
    }
}