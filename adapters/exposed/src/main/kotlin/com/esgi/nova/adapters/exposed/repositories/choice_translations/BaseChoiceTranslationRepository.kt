package com.esgi.nova.adapters.exposed.repositories.choice_translations

import com.esgi.nova.adapters.exposed.domain.*
import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

abstract class BaseChoiceTranslationRepository<ID>:
        IRepository<ID, ChoiceTranslationCmdDto<UUID>, ChoiceTranslationEntity>{

    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<ChoiceTranslationEntity> = transaction {
        val elements = ChoiceTranslationEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    override fun create(element: ChoiceTranslationCmdDto<UUID>): ChoiceTranslationEntity? = transaction {
        ChoiceTranslationEntity.new {
            description = element.description
            title = element.title
            ChoiceEntity.findById(element.choiceId)?.let { choiceEntity -> choice = choiceEntity }
            LanguageEntity.findById(element.language)?.let { languageEntity -> language = languageEntity }
        }
    }

    override fun getAll(): SizedIterable<ChoiceTranslationEntity> = transaction { ChoiceTranslationEntity.all() }

    override fun updateOne(
            element: ChoiceTranslationCmdDto<UUID>,
            id: ID
    ): ChoiceTranslationEntity? = transaction {
        getOne(id)?.also { choiceTranslationEntity ->
            choiceTranslationEntity.description = element.description
            ChoiceEntity.findById(element.choiceId)?.let { choiceEntity -> choiceTranslationEntity.choice = choiceEntity }
            LanguageEntity.findById(element.language)?.let { languageEntity -> choiceTranslationEntity.language = languageEntity }
            choiceTranslationEntity.title = element.title
        }
    }
}