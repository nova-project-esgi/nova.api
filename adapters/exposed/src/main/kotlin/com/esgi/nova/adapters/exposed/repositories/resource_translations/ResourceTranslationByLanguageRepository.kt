package com.esgi.nova.adapters.exposed.repositories.resource_translations

import com.esgi.nova.adapters.exposed.domain.*
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.models.ResourceTranslationEntity
import com.esgi.nova.adapters.exposed.repositories.event_translations.EventTranslationRepository
import com.esgi.nova.adapters.exposed.tables.ChoiceTranslation
import com.esgi.nova.adapters.exposed.tables.EventTranslation
import com.esgi.nova.adapters.exposed.tables.ResourceTranslation
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.filters.translations.FilterByEntitiesAndLanguage
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ResourceTranslationByLanguageRepository:
        ResourceTranslationRepository(),
        IRepositoryByLanguage<ResourceTranslationCmdDto<UUID>, ResourceTranslationEntity>{
    override fun getAllById(id: UUID): SizedIterable<ResourceTranslationEntity> = transaction {
        ResourceTranslationEntity.find { ChoiceTranslation.language eq id }
    }
    override fun getAllTotalById(pagination: DatabasePagination, id: UUID): ITotalCollection<ResourceTranslationEntity> = transaction {
        val elements = ResourceTranslationEntity.find { ChoiceTranslation.language eq id }
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }
    override fun getAllTotalByIds(pagination: DatabasePagination, ids: Collection<UUID>): ITotalCollection<ResourceTranslationEntity> = transaction {
        val elements = ResourceTranslationEntity.find { (ChoiceTranslation.language inList ids) }
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }
    override fun getAllFiltered(filter: FilterByEntitiesAndLanguage<UUID, UUID>): SizedIterable<ResourceTranslationEntity> =
            transaction { ResourceTranslationEntity.find {
                (ResourceTranslation.resource inList filter.entities.toList()) and (ResourceTranslation.language eq filter.language) }
            }
}