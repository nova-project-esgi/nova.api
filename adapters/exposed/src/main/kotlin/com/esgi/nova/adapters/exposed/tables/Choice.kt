package com.esgi.nova.adapters.exposed.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object Choice : UUIDTable() {
    val event: Column<EntityID<UUID>> = reference("event_id", Event)
}

