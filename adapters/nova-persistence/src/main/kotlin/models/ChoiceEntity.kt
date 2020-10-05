package models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.Choice
import tables.ChoiceResource
import java.util.*

class ChoiceEntity(id: EntityID<UUID>): UUIDEntity(id){
    companion object: UUIDEntityClass<ChoiceEntity>(Choice)
    var title by  Choice.title
    var description by Choice.description
    var resources by ResourceEntity via ChoiceResource
    var event by  EventEntity referencedOn Choice.event
}