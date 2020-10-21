package com.esgi.nova.adapters.exposed.mappers

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedIterable
import org.mapstruct.Mapper
import java.util.*

@Mapper(componentModel = "jsr330")
abstract class EntityMapper {
    fun  mapUUID(entityID: EntityID<UUID>): UUID{
        return entityID.value;
    }
}