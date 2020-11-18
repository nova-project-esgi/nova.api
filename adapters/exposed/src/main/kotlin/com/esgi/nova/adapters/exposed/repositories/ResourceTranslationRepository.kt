package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.adapters.exposed.models.ResourceTranslationEntity
import com.esgi.nova.adapters.exposed.tables.ResourceTranslation
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ResourceTranslationRepository :
        ITranslationRepository<ResourceTranslationKey<UUID>, ResourceTranslationCmdDto<UUID>, ResourceTranslationEntity> {
    override fun getAll(): SizedIterable<ResourceTranslationEntity> = transaction { ResourceTranslationEntity.all() }
    override fun getOne(id: ResourceTranslationKey<UUID>): ResourceTranslationEntity? =
            transaction {
                ResourceTranslationEntity.find { (ResourceTranslation.language eq id.language) and (ResourceTranslation.resource eq id.resourceId) }
                        .firstOrNull()
            }

    override fun create(element: ResourceTranslationCmdDto<UUID>): ResourceTranslationEntity? = transaction {
        ResourceTranslationEntity.new {
            name = element.name
            ResourceEntity.findById(element.resourceId)
                    ?.let { resourceEntity -> resource = resourceEntity }
            LanguageEntity.findById(element.language)?.let { languageEntity ->
                language = languageEntity
            }
        }
    }

    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<ResourceTranslationEntity> =
            transaction {
                val elements = ResourceTranslationEntity.all()
                TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
            }

    fun getTotalByLanguages(
            databasePagination: DatabasePagination,
            languageIds: List<UUID>
    ): TotalCollection<ResourceTranslationEntity> = transaction {
        val elements = ResourceTranslationEntity.find { ResourceTranslation.language inList languageIds }
        TotalCollection(
                elements.count(),
                elements.limit(databasePagination.size.toInt(), databasePagination.offset).toList()
        )
    }

    fun getAllByResourceIdAndLanguageId(resourceId: UUID, languageId: UUID): List<ResourceTranslationEntity> =
            transaction {
                ResourceTranslationEntity.find { (ResourceTranslation.resource eq resourceId) and (ResourceTranslation.language eq languageId) }
                        .toList()
            }

    fun getAllByResourceIdsAndLanguageId(resourceIds: List<UUID>, languageId: UUID): List<ResourceTranslationEntity> =
            transaction {
                ResourceTranslationEntity.find { (ResourceTranslation.resource inList resourceIds) and (ResourceTranslation.language eq languageId) }
                        .toList()
            }

    override fun updateOne(
            element: ResourceTranslationCmdDto<UUID>,
            id: ResourceTranslationKey<UUID>
    ) =
            transaction {
                getOne(id)?.also { resourceTranslationEntity ->
                    resourceTranslationEntity.name = element.name
                    ResourceEntity.findById(element.resourceId)
                            ?.let { resourceEntity -> resourceTranslationEntity.resource = resourceEntity }
                    LanguageEntity.findById(element.language)?.let { languageEntity ->
                        resourceTranslationEntity.language = languageEntity
                    }
                }
            }

    override fun getAllTotalByLanguage(pagination: DatabasePagination, languageId: UUID) =
            transaction {
                val elements = ResourceTranslationEntity.find { ResourceTranslation.language eq languageId }
                TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
            }


    override fun getAllByLanguage(languageId: UUID): SizedIterable<ResourceTranslationEntity> = transaction {
        ResourceTranslationEntity.find { ResourceTranslation.language eq languageId }
    }
}