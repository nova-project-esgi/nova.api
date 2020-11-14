package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.provided.services.IResourceTranslationCodesService
import com.esgi.nova.ports.required.IResourceTranslationPersistence
import com.google.inject.Inject
import java.util.*

class ResourceTranslationCodesService @Inject constructor(
    languageService: ILanguageService,
    override val persistence: IResourceTranslationPersistence
) : BaseTranslationCodesService<
        ResourceTranslationKey<String>,
        ResourceTranslationKey<UUID>,
        ResourceTranslationCmdDto<String>,
        ResourceTranslationCmdDto<UUID>,
        ResourceTranslationDto>(
    languageService,
    persistence
), IResourceTranslationCodesService {

    override fun codeInputToUuidInput(
        codesInput: ResourceTranslationCmdDto<String>,
        languageId: UUID
    ): ResourceTranslationCmdDto<UUID> {
        return ResourceTranslationCmdDto(
            codesInput.name,
            codesInput.resourceId,
            languageId
        )
    }

    override fun codeIdToUuidId(
        codeId: ResourceTranslationKey<String>,
        languageId: UUID
    ): ResourceTranslationKey<UUID> {
       return ResourceTranslationKey(codeId.resourceId,languageId)
    }


}