package com.esgi.nova.adapters.exposed.repositories.choice_translations

import com.esgi.nova.adapters.exposed.domain.*
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.tables.ChoiceTranslation
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

open class ChoiceTranslationRepository: BaseChoiceTranslationRepository<UUID>() {

    override fun getOne(id: UUID): ChoiceTranslationEntity? = transaction { ChoiceTranslationEntity[id] }

}