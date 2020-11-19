package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.ResourceTranslationMapper
import com.esgi.nova.adapters.exposed.models.ResourceTranslationEntity
import com.esgi.nova.adapters.exposed.port_implementation.translation_persistence.BaseTranslationPersistence
import com.esgi.nova.adapters.exposed.repositories.ChoiceResourceRepository
import com.esgi.nova.adapters.exposed.repositories.ResourceTranslationRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.required.ILanguagePersistence
import com.esgi.nova.ports.required.IResourceTranslationPersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class ResourceTranslationPersistence @Inject constructor(
    dbContext: DatabaseContext,
    override val repository: ResourceTranslationRepository,
    mapper: ResourceTranslationMapper,
    private val choiceResourceRepository: ChoiceResourceRepository,
    private val languagePersistence: ILanguagePersistence
) : BaseTranslationPersistence<ResourceTranslationKey<UUID>, ResourceTranslationCmdDto<UUID>, ResourceTranslationEntity, ResourceTranslationDto>(
    repository,
    mapper,
    dbContext
), IResourceTranslationPersistence {

    override fun getAllByResourceIdAndLanguageId(resourceId: UUID, languageId: UUID): Collection<ResourceTranslationDto> = dbContext.connectAndExec {
        mapper.toDtos(
            repository.getAllByResourceIdAndLanguageId(
                resourceId,
                languageId
            )
        )
    }

    override fun getTotalByLanguages(
        pagination: IPagination,
        languageIds: List<UUID>
    ): ITotalCollection<ResourceTranslationDto> = dbContext.connectAndExec {
        val totalCollection =
            repository.getTotalByLanguages(DatabasePagination(pagination), languageIds)
        TotalCollection(
            totalCollection.total,
            mapper.toDtos(
                totalCollection
            )
        )
    }

    override fun getAllByChoiceIdAndLanguageId(choiceId: UUID, languageId: UUID): Collection<ResourceTranslationDto> =
        dbContext.connectAndExec {
            val resourceIds = choiceResourceRepository.getAllByChoiceId(choiceId)
                .map { choiceResource -> choiceResource.resource.id.value }
            mapper.toDtos(
                repository.getAllByResourceIdsAndLanguageId(
                    resourceIds,
                    languageId
                )
            )
        }

    override fun getOneDefault(resourceId: UUID): ResourceTranslationDto? = dbContext.connectAndExec {
        languagePersistence.getDefault()?.let { languageDto ->
            mapper.toDto(
                repository.getOne(
                    ResourceTranslationKey<UUID>(
                        resourceId,
                        languageDto.id
                    )
                )
            )
        }
    }

    override fun getAllDefaultByChoiceId(choiceId: UUID): Collection<ResourceTranslationDto> {
        languagePersistence.getDefault()?.let { language ->
            return getAllByChoiceIdAndLanguageId(choiceId, language.id)
        }
        return listOf()
    }


}