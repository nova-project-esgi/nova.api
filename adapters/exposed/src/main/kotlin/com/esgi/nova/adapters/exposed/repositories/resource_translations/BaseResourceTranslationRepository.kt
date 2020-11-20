package com.esgi.nova.adapters.exposed.repositories.resource_translations

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.adapters.exposed.models.ResourceTranslationEntity
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

abstract class BaseResourceTranslationRepository<ID>: IRepository<ID, ResourceTranslationCmdDto<UUID>, ResourceTranslationEntity> {
    override fun getAll(): SizedIterable<ResourceTranslationEntity> = transaction { ResourceTranslationEntity.all() }
    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<ResourceTranslationEntity> = transaction {
        val elements = ResourceTranslationEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
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
    override fun updateOne(element: ResourceTranslationCmdDto<UUID>, id: ID): ResourceTranslationEntity? =
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
}