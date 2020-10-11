package com.esgi.nova.adapters.exposed.models

import com.esgi.nova.adapters.exposed.tables.ChoiceResource
import com.esgi.nova.adapters.exposed.tables.Resource
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class ResourceEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ResourceEntity>(Resource)

    var name by Resource.name
    var choices by ChoiceEntity via ChoiceResource
}