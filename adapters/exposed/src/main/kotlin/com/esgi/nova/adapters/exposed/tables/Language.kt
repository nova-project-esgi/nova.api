package com.esgi.nova.adapters.exposed.tables

import com.esgi.nova.ports.common.constants.StringLength
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

object Language : UUIDTable() {
    val code: Column<String> = varchar("code", StringLength.SHORT_STRING)
    val subCode: Column<String?> = varchar("sub_code", StringLength.SHORT_STRING).nullable()
}