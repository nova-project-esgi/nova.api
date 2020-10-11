package com.esgi.nova.adapters.exposed.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object ChoiceResource : UUIDTable() {
    val choice: Column<EntityID<UUID>> = reference("choice_id", Choice)
    val resource: Column<EntityID<UUID>> = reference("resource_id", Resource)
    val changeValue: Column<Int> = integer("change_value")
}