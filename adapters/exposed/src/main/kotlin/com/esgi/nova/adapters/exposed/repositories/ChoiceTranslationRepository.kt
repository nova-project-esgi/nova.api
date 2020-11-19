package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.ITranslationRepository
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.adapters.exposed.tables.ChoiceTranslation
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ChoiceTranslationRepository: ITranslationRepository<ChoiceTranslationKey<UUID>, ChoiceTranslationCmdDto<UUID>, ChoiceTranslationEntity> {
    override fun getAll(): SizedIterable<ChoiceTranslationEntity> = transaction { ChoiceTranslationEntity.all() }
    fun getOne(id: UUID): ChoiceTranslationEntity? = transaction { ChoiceTranslationEntity[id] }
    override fun getOne(id: ChoiceTranslationKey<UUID>): ChoiceTranslationEntity? =
            transaction {
                ChoiceTranslationEntity.find { (ChoiceTranslation.choice eq id.entityId) and (ChoiceTranslation.language eq id.language) }
                        .firstOrNull()
            }

    override fun create(element: ChoiceTranslationCmdDto<UUID>): ChoiceTranslationEntity? = transaction {
        ChoiceTranslationEntity.new {
            description = element.description
            title = element.title
            ChoiceEntity.findById(element.choiceId)?.let { choiceEntity -> choice = choiceEntity }
            LanguageEntity.findById(element.language)?.let { languageEntity -> language = languageEntity }
        }
    }

    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<ChoiceTranslationEntity> = transaction {
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

    override fun updateOne(
            element: ChoiceTranslationCmdDto<UUID>,
            id: ChoiceTranslationKey<UUID>
    ): ChoiceTranslationEntity? = transaction {
        getOne(id)?.also { choiceTranslationEntity ->
            choiceTranslationEntity.description = element.description
            ChoiceEntity.findById(element.choiceId)?.let { choiceEntity -> choiceTranslationEntity.choice = choiceEntity }
            LanguageEntity.findById(element.language)?.let { languageEntity -> choiceTranslationEntity.language = languageEntity }
            choiceTranslationEntity.title = element.title
        }
    }

    override fun getAllTotalByLanguage(pagination: DatabasePagination, languageId: UUID): ITotalCollection<ChoiceTranslationEntity> = transaction {
        val elements = ChoiceTranslationEntity.find{ChoiceTranslation.language eq languageId}
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    override fun getAllByLanguage(languageId: UUID): SizedIterable<ChoiceTranslationEntity> = transaction {
        ChoiceTranslationEntity.find{ChoiceTranslation.language eq languageId}
    }
}