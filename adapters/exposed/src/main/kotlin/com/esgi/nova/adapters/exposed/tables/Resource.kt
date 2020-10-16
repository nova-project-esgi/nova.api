package com.esgi.nova.adapters.exposed.tables

import com.esgi.nova.common.StringLength
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

object Resource : UUIDTable() {
    val name: Column<String> = varchar("name", StringLength.MEDIUM_STRING)
}