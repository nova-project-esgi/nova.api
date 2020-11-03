package com.esgi.nova.domain.services

import com.esgi.nova.domain.extensions.toPage
import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.*
import com.esgi.nova.ports.provided.dtos.choice.translated_choice_detailed.TranslatedChoiceDetailedDto
import com.esgi.nova.ports.provided.dtos.choice.translated_choice_detailed.TranslatedResourceDetailedDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationLanguageIdCmdDto
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventDto
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventWithoutRelationsDto
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceDto
import com.esgi.nova.ports.provided.services.*
import com.esgi.nova.ports.required.IChoicePersistence
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.esgi.nova.ports.required.IChoiceTranslationPersistence
import com.google.inject.Inject
import java.util.*

class ChoiceService @Inject constructor(
    private val choicePersistence: IChoicePersistence,
    private val choiceResourcePersistence: IChoiceResourcePersistence,
    private val choiceTranslationPersistence: IChoiceTranslationPersistence,
    private val choiceTranslationService: IChoiceTranslationService,
    private val resourceService: IResourceService,
    private val languageService: ILanguageService,
    private val eventService: IEventService,
    private val choiceResourceService: IChoiceResourceService
) : IChoiceService {

    private fun handleUseDefaultForOne(id: ChoiceTranslationKey, useDefault: Boolean = false): ChoiceTranslationDto? {
        return if (useDefault) {
            choiceTranslationService.getOneOrDefault(id)
        } else {
            choiceTranslationService.getOne(id)
        }
    }

    override fun createChoiceAndAttachResources(element: ChoiceWithResourcesCmdDto): ChoiceDto? {
        val choice = choicePersistence.create(element)
        choice?.let {
            element.resources.forEach { resourceChange ->
                val choiceResource = ChoiceResourceCmdDto(
                    choiceId = choice.id,
                    resourceId = resourceChange.resourceId,
                    changeValue = resourceChange.changeValue
                )
                choiceResourcePersistence.create(choiceResource)
            }
        }
        return choice
    }


    override fun getTranslatedChoicesByIdsAndLanguageId(
        choiceIds: List<UUID>,
        languageId: UUID,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguages: Boolean
    ): List<TranslatedChoiceDto> {
        val choiceTranslations = if (useDefaultLanguages) {
            choiceTranslationService.getAllByChoiceIdsAndLanguageIdWithDefaults(choiceIds, languageId)
        } else {
            choiceTranslationPersistence.getAllByChoiceIdsAndLanguageId(choiceIds, languageId)
        }
        return choiceTranslations.map { choiceTranslation ->
            convertChoiceTranslationToTranslatedChoice(
                choiceTranslation,
                choiceTranslation.language.id,
                includeEvent,
                includeResources
            )
        }
    }

    override fun getTranslatedChoicesByEventIdAndLanguageId(
        eventId: UUID,
        languageId: UUID,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguage: Boolean
    ): List<TranslatedChoiceDto> {
        val choiceIds = choicePersistence.getAllByEventId(eventId).map { choiceDto -> choiceDto.id }
        return getTranslatedChoicesByIdsAndLanguageId(
            choiceIds,
            languageId,
            includeEvent,
            includeResources,
            useDefaultLanguage
        )
    }

    override fun getTranslatedChoicesByResourceIdAndLanguageId(
        resourceId: UUID,
        languageId: UUID,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguage: Boolean
    ): List<TranslatedChoiceDto> {
        val choiceIds =
            choiceResourcePersistence.getAllByResourceId(resourceId).map { choiceDto -> choiceDto.choice.id }
        return getTranslatedChoicesByIdsAndLanguageId(
            choiceIds,
            languageId,
            includeEvent,
            includeResources,
            useDefaultLanguage
        )
    }

    override fun createTranslatedChoiceAndAttachResources(
        element: TranslatedChoiceWithResourcesCmdDto,
        codes: String
    ): TranslatedChoiceDto? {
        languageService.getOneByCodes(codes)?.let { language ->
            choicePersistence.create(element)?.let { choice ->
                element.resources.forEach { resource ->
                    choiceResourcePersistence.create(
                        ChoiceResourceCmdDto(
                            choice.id,
                            resource.resourceId,
                            resource.changeValue
                        )
                    )
                }
                choiceTranslationPersistence.create(
                    ChoiceTranslationLanguageIdCmdDto(
                        element.title,
                        element.description,
                        choice.id,
                        language.id
                    )
                )?.let { choiceTranslation ->
                    return convertChoiceTranslationToTranslatedChoice(
                        choiceTranslation, language.id
                    )
                }
            }
        }
        return null
    }


    override fun getTranslatedChoice(
        id: UUID,
        codes: String,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedChoiceDto? {
        languageService.getOneByCodes(codes)?.let { language ->
            return handleUseDefaultForOne(ChoiceTranslationKey(id, language.id))
                ?.let { choiceTranslation ->
                    return convertChoiceTranslationToTranslatedChoice(
                        choiceTranslation, language.id, includeEvent, includeResources
                    )
                }
        }
        return null
    }

    override fun getTranslatedChoiceDetailed(
        id: UUID,
        codes: String,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedChoiceDetailedDto? {
        getTranslatedChoice(id, codes, includeEvent, includeResources, useDefaultLanguage)?.let { translatedChoice ->
            return convertTranslatedChoiceToTranslatedChoiceDetailed(translatedChoice)
        }
        return null
    }

    override fun convertTranslatedChoiceToTranslatedChoiceDetailed(translatedChoice: TranslatedChoiceDto): TranslatedChoiceDetailedDto? {
        translatedChoice.event?.let { event ->
            val translatedChoiceDetail =
                TranslatedChoiceDetailedDto(translatedChoice, TranslatedEventWithoutRelationsDto(event))
            setChangeValueToTranslatedResourceDetailed(translatedChoiceDetail.resources, translatedChoice.id)
            return translatedChoiceDetail
        }
        return null
    }

    override fun setChangeValueToTranslatedResourceDetailed(
        resources: List<TranslatedResourceDetailedDto>,
        choiceId: UUID
    ) {
        val choiceResources = choiceResourceService.getAllByChoiceId(choiceId)
        choiceResources.forEach { choiceResource ->
            resources.firstOrNull { resource -> resource.id == choiceResource.resource.id }
                ?.let { resource ->
                    resource.changeValue = choiceResource.changeValue
                }
        }
    }


    override fun getTranslatedChoice(
        id: UUID,
        languageId: UUID,
        includeEvent: Boolean,
        includeResources: Boolean,
        useDefaultLanguage: Boolean
    ): TranslatedChoiceDto? {
        return handleUseDefaultForOne(ChoiceTranslationKey(id, languageId))
            ?.let { choiceTranslation ->
                return convertChoiceTranslationToTranslatedChoice(
                    choiceTranslation, languageId,
                    includeEvent,
                    includeResources
                )
            }
    }

    override fun getTranslatedChoicesPage(
        pagination: IPagination,
        codes: String,
        includeEvent: Boolean,
        includeResources: Boolean
    ): IPage<TranslatedChoiceDto> {
        val translations = choiceTranslationPersistence.getTotalByLanguages(
            pagination,
            languageService.getAllByCodes(codes).map { l -> l.id })
        return translations.map { t ->
            convertChoiceTranslationToTranslatedChoice(
                t,
                t.language.id,
                includeEvent,
                includeResources
            )
        }
            .toStaticPage(pagination, translations.total.toInt())
    }

    override fun createTranslatedChoice(translatedChoice: TranslatedChoiceCmdDto, codes: String): TranslatedChoiceDto? {
        languageService.getOneByCodes(codes)?.let { language ->
            choicePersistence.create(ChoiceCmdDto(translatedChoice.eventId))?.let { choice ->
                choiceTranslationPersistence.create(
                    ChoiceTranslationLanguageIdCmdDto(
                        translatedChoice.title,
                        translatedChoice.description,
                        choice.id,
                        language.id
                    )
                )?.let { choiceTranslation ->
                    return convertChoiceTranslationToTranslatedChoice(
                        choiceTranslation, languageId = language.id
                    )
                }
            }
        }
        return null
    }

    private fun convertChoiceTranslationToTranslatedChoice(
        choiceTranslation: ChoiceTranslationDto,
        languageId: UUID,
        includeEvent: Boolean = true,
        includeResource: Boolean = true
    ): TranslatedChoiceDto {
        var translatedEventDto: TranslatedEventDto? = null
        var translatedResources: Collection<TranslatedResourceDto>? = null
        if (includeEvent) {
            choiceTranslation.choice.event.let { event ->
                translatedEventDto = eventService.getTranslatedEvent(
                    event.id,
                    languageId,
                    includeChoices = false,
                    useDefaultLanguage = true
                )
            }
        }
        if (includeResource) {
            translatedResources =
                resourceService.getTranslatedResourcesByChoiceIdAndLanguageId(
                    choiceTranslation.choice.id,
                    languageId,
                    includeChoices = false,
                    useDefaultLanguage = true
                )
        }
        return choiceTranslation.toTranslatedChoiceDto(
            translatedEventDto,
            translatedResources?.toList() ?: listOf()
        )
    }

    override fun getAll() = choicePersistence.getAll()
    override fun getOne(id: UUID): ChoiceDto? = choicePersistence.getOne(id)

    override fun create(element: ChoiceCmdDto): ChoiceDto? = choicePersistence.create(element)
    override fun getPage(pagination: IPagination): IPage<ChoiceDto> =
        choicePersistence.getAllTotal(pagination).toPage(pagination)
}