package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.*
import com.esgi.nova.adapters.exposed.tables.ChoiceResource
import com.esgi.nova.adapters.exposed.tables.ResourceTranslation
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationLanguageIdCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ResourceTranslationRepository {
    fun getAll(): SizedIterable<ResourceTranslationEntity> = transaction { ResourceTranslationEntity.all() }
    fun getOne(id: ResourceTranslationKey): ResourceTranslationEntity? =
        transaction {
            ResourceTranslationEntity.find { (ResourceTranslation.language eq id.languageId) and (ResourceTranslation.resource eq id.resourceId) }
                .firstOrNull()
        }

    fun create(resourceTranslation: ResourceTranslationLanguageIdCmdDto): ResourceTranslationEntity? = transaction {
        ResourceTranslationEntity.new {
            name = resourceTranslation.name
            resource = ResourceEntity[resourceTranslation.resourceId]
            language = LanguageEntity[resourceTranslation.languageId]
        }
    }

    fun getAllTotal(pagination: DatabasePagination): ITotalCollection<ResourceTranslationEntity> = transaction {
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

    fun getAllByResourceIdsAndLanguageId(resourceIds: List<UUID>, languageId: UUID): List<ResourceTranslationEntity> = transaction {
        ResourceTranslationEntity.find { (ResourceTranslation.resource inList resourceIds) and (ResourceTranslation.language eq languageId) }.toList()
    }

//    fun getAllByChoiceIdAndLanguageId(choiceId: UUID, languageId: UUID): List<ResourceTranslationEntity> = transaction {
//        ChoiceResourceEntity.find { ChoiceResource.choice eq choiceId }.map { choiceResourceEntity -> choiceResourceEntity. }
////        ChoiceEntity.findById(choiceId)?.also { choiceEntity ->
////            choiceEntity.resources.
////        }
//    }
}