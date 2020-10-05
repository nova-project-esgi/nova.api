package models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.ChoiceResource
import tables.Resource
import java.util.*

class ResourceEntity(id: EntityID<UUID>): UUIDEntity(id){
    companion object: UUIDEntityClass<ResourceEntity>(Resource)
    var name  by Resource.name
    var choices by ChoiceEntity via ChoiceResource
}