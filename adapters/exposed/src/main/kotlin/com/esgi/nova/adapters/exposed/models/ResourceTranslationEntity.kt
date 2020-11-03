package com.esgi.nova.adapters.exposed.models

import com.esgi.nova.adapters.exposed.tables.ResourceTranslation
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


class ResourceTranslationEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ResourceTranslationEntity>(ResourceTranslation)

    var name by ResourceTranslation.name
    var resource by ResourceEntity referencedOn ResourceTranslation.resource
    var language by LanguageEntity referencedOn ResourceTranslation.language
}