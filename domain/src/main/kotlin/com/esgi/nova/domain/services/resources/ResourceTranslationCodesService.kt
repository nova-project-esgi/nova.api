package com.esgi.nova.domain.services.resources

import com.esgi.nova.domain.services.translation_service.BaseTranslationCodesService
import com.esgi.nova.domain.services.translation_service.ICodeIdToUuidId
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.provided.services.resources.IResourceTranslationCodesService
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

    override var codeMapper: ICodeIdToUuidId<ResourceTranslationKey<String>, ResourceTranslationKey<UUID>> = this
    override fun transformInput(
        codesInput: ResourceTranslationCmdDto<String>,
        languageId: UUID
    ): ResourceTranslationCmdDto<UUID> {
        return ResourceTranslationCmdDto(
            codesInput.name,
            codesInput.resourceId,
            languageId
        )
    }

    override fun transformId(
        codeId: ResourceTranslationKey<String>,
        languageId: UUID
    ): ResourceTranslationKey<UUID> {
       return ResourceTranslationKey(codeId.resourceId,languageId)
    }



}