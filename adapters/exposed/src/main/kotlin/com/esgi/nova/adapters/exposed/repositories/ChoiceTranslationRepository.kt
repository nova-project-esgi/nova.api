package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.adapters.exposed.tables.ChoiceTranslation
import com.esgi.nova.ports.provided.dtos.choice.ChoiceTranslationKey
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationLanguageIdCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ChoiceTranslationRepository {
    fun getAll(): SizedIterable<ChoiceTranslationEntity> = transaction { ChoiceTranslationEntity.all() }
    fun getOne(id: UUID): ChoiceTranslationEntity? = transaction { ChoiceTranslationEntity[id] }
    fun getOne(id: ChoiceTranslationKey): ChoiceTranslationEntity? =
        transaction {
            ChoiceTranslationEntity.find { (ChoiceTranslation.choice eq id.choiceId) and (ChoiceTranslation.language eq id.languageId) }
                .firstOrNull()
        }

    fun create(choiceTranslation: ChoiceTranslationLanguageIdCmdDto): ChoiceTranslationEntity? = transaction {
        ChoiceTranslationEntity.new {
            description = choiceTranslation.description
            title = choiceTranslation.title
            choice = ChoiceEntity[choiceTranslation.choiceId]
            language = LanguageEntity[choiceTranslation.languageId]
        }
    }

    fun getAllTotal(pagination: DatabasePagination): ITotalCollection<ChoiceTranslationEntity> = transaction {
        val elements = ChoiceTranslationEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    fun getTotalByLanguages(pagination: DatabasePagination, languageIds: List<UUID>) = transaction {
        val elements = ChoiceTranslationEntity.find { (ChoiceTranslation.language inList languageIds) }
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    fun getAllByChoiceIdAndLanguageId(choiceId: UUID, languageId: UUID) =
        transaction {
            ChoiceTranslationEntity.find { (ChoiceTranslation.choice eq choiceId) and (ChoiceTranslation.language eq languageId) }
        }

    fun getAllByChoiceIdsAndLanguageId(choiceIds: List<UUID>, languageId: UUID) =
        transaction { ChoiceTranslationEntity.find { (ChoiceTranslation.choice inList choiceIds) and (ChoiceTranslation.language eq languageId) } }
}