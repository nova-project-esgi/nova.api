package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.ResourceTranslationMapper
import com.esgi.nova.adapters.exposed.repositories.ChoiceResourceRepository
import com.esgi.nova.adapters.exposed.repositories.ResourceTranslationRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationLanguageIdCmdDto
import com.esgi.nova.ports.required.ILanguagePersistence
import com.esgi.nova.ports.required.IResourceTranslationPersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class ResourceTranslationPersistence @Inject constructor(
    private val dbContext: DatabaseContext,
    private val resourceTranslationRepository: ResourceTranslationRepository,
    private val resourceTranslationMapper: ResourceTranslationMapper,
    private val choiceResourceRepository: ChoiceResourceRepository,
    private val languagePersistence: ILanguagePersistence
) : IResourceTranslationPersistence {
    override fun getAll(): Collection<ResourceTranslationDto> =
        dbContext.connectAndExec {
            resourceTranslationMapper.toDtos(
                resourceTranslationRepository.getAll().toList()
            )
        }

    override fun create(element: ResourceTranslationLanguageIdCmdDto): ResourceTranslationDto? =
        dbContext.connectAndExec { resourceTranslationMapper.toDto(resourceTranslationRepository.create(element)) }

    override fun getAllTotal(pagination: IPagination): ITotalCollection<ResourceTranslationDto> =
        dbContext.connectAndExec {
            val totalCollection = resourceTranslationRepository.getAllTotal(DatabasePagination(pagination))
            TotalCollection(
                totalCollection.total,
                resourceTranslationMapper.toDtos(
                    totalCollection
                )
            )
        }

    override fun getOne(id: ResourceTranslationKey): ResourceTranslationDto? =
        dbContext.connectAndExec {
            resourceTranslationRepository.getOne(id)?.let { resource -> resourceTranslationMapper.toDto(resource) }
        }

    override fun getAllByResourceIdAndLanguageId(resourceId: UUID, languageId: UUID) = dbContext.connectAndExec {
        resourceTranslationMapper.toDtos(
            resourceTranslationRepository.getAllByResourceIdAndLanguageId(
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
            resourceTranslationRepository.getTotalByLanguages(DatabasePagination(pagination), languageIds)
        TotalCollection(
            totalCollection.total,
            resourceTranslationMapper.toDtos(
                totalCollection
            )
        )
    }

    override fun getAllByChoiceIdAndLanguageId(choiceId: UUID, languageId: UUID): Collection<ResourceTranslationDto> =
        dbContext.connectAndExec {
            val resourceIds = choiceResourceRepository.getAllByChoiceId(choiceId)
                .map { choiceResource -> choiceResource.resource.id.value }
            resourceTranslationMapper.toDtos(
                resourceTranslationRepository.getAllByResourceIdsAndLanguageId(
                    resourceIds,
                    languageId
                )
            )
        }

    override fun getOneDefault(resourceId: UUID): ResourceTranslationDto? = dbContext.connectAndExec {
        languagePersistence.getDefault()?.let { languageDto ->
            resourceTranslationMapper.toDto(
                resourceTranslationRepository.getOne(
                    ResourceTranslationKey(
                        resourceId,
                        languageDto.id
                    )
                )
            )
        }
    }

    override fun getAllDefaultByChoiceId(choiceId: UUID): Collection<ResourceTranslationDto> {
        languagePersistence.getDefault()?.let {language ->
            return getAllByChoiceIdAndLanguageId(choiceId, language.id)
        }
        return listOf()
    }

}