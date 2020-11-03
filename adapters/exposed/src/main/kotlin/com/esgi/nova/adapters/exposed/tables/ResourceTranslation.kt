package com.esgi.nova.adapters.exposed.tables

import com.esgi.nova.ports.common.constants.StringLength
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object ResourceTranslation : UUIDTable("Resource_Translation") {
    val name: Column<String> = varchar("name", StringLength.MEDIUM_STRING)
    val resource: Column<EntityID<UUID>> = reference("resource_id", Resource)
    val language: Column<EntityID<UUID>> = reference("language_id", Language)
}