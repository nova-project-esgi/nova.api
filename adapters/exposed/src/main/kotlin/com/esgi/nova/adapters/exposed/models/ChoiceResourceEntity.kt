package com.esgi.nova.adapters.exposed.models

import com.esgi.nova.adapters.exposed.tables.ChoiceResource
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class ChoiceResourceEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ChoiceResourceEntity>(ChoiceResource)

    var choice by ChoiceEntity referencedOn ChoiceResource.choice
    var resource by ResourceEntity referencedOn ChoiceResource.resource
    var changeValue by ChoiceResource.changeValue
}