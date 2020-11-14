package com.esgi.nova.adapters.exposed.tables

import com.esgi.nova.ports.common.constants.StringLength
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column


object User : UUIDTable() {
    val username: Column<String> = varchar("username", StringLength.LONG_STRING)
    val password: Column<String> = varchar("password", StringLength.LONG_STRING)
    val email: Column<String> = varchar("email", StringLength.LONG_STRING)
    val role: Column<String> = varchar("role", StringLength.MEDIUM_STRING)
}