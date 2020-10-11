package com.esgi.nova.adapters.exposed.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime
import java.util.*

object GameEvent : UUIDTable() {
    val game: Column<EntityID<UUID>> = reference("game_id", Game)
    val event: Column<EntityID<UUID>> = reference("event_id", Event)
    val linkTime: Column<LocalDateTime> = datetime("link_time")
}