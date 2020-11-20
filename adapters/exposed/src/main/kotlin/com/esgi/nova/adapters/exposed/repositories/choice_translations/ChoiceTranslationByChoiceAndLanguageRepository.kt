package com.esgi.nova.adapters.exposed.repositories.choice_translations

import com.esgi.nova.adapters.exposed.domain.IGetAllIterable
import com.esgi.nova.adapters.exposed.domain.IGetAllIterableById
import com.esgi.nova.adapters.exposed.domain.IGetAllIterableFiltered
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.tables.ChoiceTranslation
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import com.esgi.nova.ports.provided.filters.translations.FilterByEntitiesAndLanguage
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ChoiceTranslationByChoiceAndLanguageRepository:
        BaseChoiceTranslationRepository<ChoiceTranslationKey<UUID>>(),
        IGetAllIterableById<ChoiceTranslationKey<UUID>, ChoiceTranslationEntity>{
    override fun getOne(id: ChoiceTranslationKey<UUID>): ChoiceTranslationEntity? =
            transaction {
                ChoiceTranslationEntity.find { (ChoiceTranslation.choice eq id.entityId) and (ChoiceTranslation.language eq id.language) }
                        .firstOrNull()
            }

    override fun getAllById(id: ChoiceTranslationKey<UUID>): SizedIterable<ChoiceTranslationEntity> =
            transaction {
                ChoiceTranslationEntity.find { (ChoiceTranslation.choice eq id.entityId) and (ChoiceTranslation.language eq id.language) }
            }

}