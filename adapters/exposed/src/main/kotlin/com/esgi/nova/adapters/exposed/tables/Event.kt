package com.esgi.nova.adapters.exposed.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

object Event : UUIDTable() {
    val isDaily: Column<Boolean> = bool("is_daily")
    val isActive: Column<Boolean> = bool("is_active")
}