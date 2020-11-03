package com.esgi.nova.adapters.exposed.tables

import com.esgi.nova.ports.common.constants.StringLength
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object EventTranslation : UUIDTable("Event_Translation") {
    val title: Column<String> = varchar("title", StringLength.LONG_STRING)
    val description: Column<String> = text("description")
    val event: Column<EntityID<UUID>> = reference("event_id", Event)
    val language: Column<EntityID<UUID>> = reference("language_id", Language)
}