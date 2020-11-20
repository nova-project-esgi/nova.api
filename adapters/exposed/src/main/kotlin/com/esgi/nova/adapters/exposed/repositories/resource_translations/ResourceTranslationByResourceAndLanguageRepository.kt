package com.esgi.nova.adapters.exposed.repositories.resource_translations

import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.models.ResourceTranslationEntity
import com.esgi.nova.adapters.exposed.tables.ResourceTranslation
import com.esgi.nova.ports.common.IGetAllById
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ResourceTranslationByResourceAndLanguageRepository:
        BaseResourceTranslationRepository<ResourceTranslationKey<UUID>>(),
        IRepository<ResourceTranslationKey<UUID>, ResourceTranslationCmdDto<UUID>, ResourceTranslationEntity>,
        IGetAllById<ResourceTranslationKey<UUID>, ResourceTranslationEntity> {
    override fun getOne(id: ResourceTranslationKey<UUID>): ResourceTranslationEntity? =
            transaction {
                ResourceTranslationEntity.find { (ResourceTranslation.language eq id.language) and (ResourceTranslation.resource eq id.entityId) }
                        .firstOrNull()
            }

    override fun getAllById(id: ResourceTranslationKey<UUID>): Collection<ResourceTranslationEntity> =
            transaction {
                ResourceTranslationEntity.find { (ResourceTranslation.resource eq id.entityId) and (ResourceTranslation.language eq id.language) }
                        .toList()
            }

}