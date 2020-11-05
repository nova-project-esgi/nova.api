package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.provided.services.IResourceTranslationCodesService
import com.esgi.nova.ports.required.IResourceTranslationPersistence
import com.google.inject.Inject

class ResourceTranslationCodesService @Inject constructor(
    private val languageService: ILanguageService,
    private val resourceTranslationPersistence: IResourceTranslationPersistence
) : IResourceTranslationCodesService {

    override fun getOne(id: ResourceTranslationKey<String>): ResourceTranslationDto? {
        languageService.getOneByCodes(id.language)?.let { l ->
            return resourceTranslationPersistence.getOne(ResourceTranslationKey(id.resourceId, l.id))
        }
        return null
    }

    override fun create(element: ResourceTranslationCmdDto<String>): ResourceTranslationDto? {
        languageService.getOneByCodes(element.language)?.let { l ->
            return resourceTranslationPersistence.create(
                ResourceTranslationCmdDto(
                    element.name,
                    element.resourceId,
                    l.id
                )
            )
        }
        return null
    }

    override fun updateOne(
        element: ResourceTranslationCmdDto<String>,
        id: ResourceTranslationKey<String>
    ): ResourceTranslationDto? {
        languageService.getOneByCodes(element.language)?.let { l ->
            return resourceTranslationPersistence.updateOne(
                ResourceTranslationCmdDto(
                    element.name,
                    element.resourceId,
                    l.id
                ),
                ResourceTranslationKey(id.resourceId, l.id)
            )
        }
        return null
    }


}