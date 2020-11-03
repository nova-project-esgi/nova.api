package com.esgi.nova.adapters.exposed.models

import com.esgi.nova.adapters.exposed.tables.Choice
import com.esgi.nova.adapters.exposed.tables.ChoiceResource
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class ChoiceEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ChoiceEntity>(Choice)

    private var _resources: List<ResourceEntity>? = null
    var resourcesIterable by ResourceEntity via ChoiceResource
    val resources
        get() :List<ResourceEntity> {
            _resources = _resources ?: resourcesIterable.toList()
            return _resources!!
        }
    var event by EventEntity referencedOn Choice.event
}