package com.esgi.nova.adapters.exposed.port_implementation.resource_translations

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ResourceTranslationMapper
import com.esgi.nova.adapters.exposed.models.ResourceTranslationEntity
import com.esgi.nova.adapters.exposed.port_implementation.translation_persistence.BaseDefaultTranslationPersistence
import com.esgi.nova.adapters.exposed.repositories.resource_translations.ResourceTranslationByLanguageRepository
import com.esgi.nova.adapters.exposed.repositories.resource_translations.ResourceTranslationByResourceAndLanguageRepository
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.required.ILanguagePersistence
import java.util.*

class DefaultResourceTranslationPersistence(override val repository: ResourceTranslationByResourceAndLanguageRepository,
                                            repositoryByLanguage: ResourceTranslationByLanguageRepository,
                                            languagePersistence: ILanguagePersistence,
                                            mapper: ResourceTranslationMapper, dbContext: DatabaseContext): BaseDefaultTranslationPersistence<
        ResourceTranslationKey<UUID>,
        ResourceTranslationCmdDto<UUID>,
        ResourceTranslationEntity, ResourceTranslationDto>(repository, repositoryByLanguage, languagePersistence, mapper, dbContext) {
    override fun toTranslationKey(entityId: UUID, languageId: UUID): ResourceTranslationKey<UUID> = ResourceTranslationKey(entityId, languageId)
}