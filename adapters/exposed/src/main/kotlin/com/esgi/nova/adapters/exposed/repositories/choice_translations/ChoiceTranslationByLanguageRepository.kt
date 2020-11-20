package com.esgi.nova.adapters.exposed.repositories.choice_translations

import com.esgi.nova.adapters.exposed.domain.*
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.tables.ChoiceTranslation
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.filters.translations.FilterByEntitiesAndLanguage
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ChoiceTranslationByLanguageRepository :
        ChoiceTranslationRepository(),IRepositoryByLanguage<ChoiceTranslationCmdDto<UUID>, ChoiceTranslationEntity> {
    override fun getAllById(id: UUID): SizedIterable<ChoiceTranslationEntity> = transaction {
        ChoiceTranslationEntity.find { ChoiceTranslation.language eq id }
    }

    override fun getAllTotalById(pagination: DatabasePagination, id: UUID): ITotalCollection<ChoiceTranslationEntity> = transaction {
        val elements = ChoiceTranslationEntity.find { ChoiceTranslation.language eq id }
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    override fun getAllTotalByIds(pagination: DatabasePagination, ids: Collection<UUID>): ITotalCollection<ChoiceTranslationEntity> = transaction {
        val elements = ChoiceTranslationEntity.find { (ChoiceTranslation.language inList ids) }
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    override fun getAllFiltered(filter: FilterByEntitiesAndLanguage<UUID, UUID>): SizedIterable<ChoiceTranslationEntity> =
            transaction { ChoiceTranslationEntity.find {
                (ChoiceTranslation.choice inList filter.entities.toList()) and (ChoiceTranslation.language eq filter.language) }
            }


}