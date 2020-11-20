package com.esgi.nova.adapters.exposed.repositories.event_translations

import com.esgi.nova.adapters.exposed.domain.*
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.tables.ChoiceTranslation
import com.esgi.nova.adapters.exposed.tables.EventTranslation
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.filters.translations.FilterByEntitiesAndLanguage
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class EventTranslationByLanguageRepository:
        EventTranslationRepository(),
        IRepositoryByLanguage<EventTranslationCmdDto<UUID>, EventTranslationEntity>{
    override fun getAllById(id: UUID): SizedIterable<EventTranslationEntity> = transaction {
        EventTranslationEntity.find { EventTranslation.language eq id }
    }

    override fun getAllTotalById(pagination: DatabasePagination, id: UUID): ITotalCollection<EventTranslationEntity> = transaction {
        val elements = EventTranslationEntity.find { EventTranslation.language eq id }
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    override fun getAllTotalByIds(pagination: DatabasePagination, ids: Collection<UUID>): ITotalCollection<EventTranslationEntity> = transaction {
        val elements = EventTranslationEntity.find { (EventTranslation.language inList ids) }
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    override fun getAllFiltered(filter: FilterByEntitiesAndLanguage<UUID, UUID>): SizedIterable<EventTranslationEntity> =
            transaction { EventTranslationEntity.find {
                (EventTranslation.event inList filter.entities.toList()) and (EventTranslation.language eq filter.language) }
            }

}