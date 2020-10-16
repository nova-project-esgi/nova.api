package com.esgi.nova.adapters.exposed.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime
import java.util.*

object Game : UUIDTable() {
    val user: Column<EntityID<UUID>> = reference("User", User)
    val startDate: Column<LocalDateTime> = datetime("start_date")
    val score: Column<Int> = integer("score")
}