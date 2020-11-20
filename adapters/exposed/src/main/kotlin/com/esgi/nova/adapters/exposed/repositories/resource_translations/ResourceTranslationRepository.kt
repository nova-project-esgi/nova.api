package com.esgi.nova.adapters.exposed.repositories.resource_translations

import com.esgi.nova.adapters.exposed.domain.*
import com.esgi.nova.adapters.exposed.models.*
import com.esgi.nova.adapters.exposed.tables.ResourceTranslation
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

open class ResourceTranslationRepository :
        BaseResourceTranslationRepository<UUID>(),
        IRepository<UUID, ResourceTranslationCmdDto<UUID>, ResourceTranslationEntity> {

    fun getAllByResourceIdsAndLanguageId(resourceIds: List<UUID>, languageId: UUID): List<ResourceTranslationEntity> =
            transaction {
                ResourceTranslationEntity.find { (ResourceTranslation.resource inList resourceIds) and (ResourceTranslation.language eq languageId) }
                        .toList()
            }

    override fun getOne(id: UUID): ResourceTranslationEntity? = transaction{ResourceTranslationEntity.findById(id)}
}