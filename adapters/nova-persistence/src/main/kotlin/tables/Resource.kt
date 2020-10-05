package tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

object Resource : UUIDTable() {
    val name: Column<String> = varchar("name", StringLength.MEDIUM_STRING)
}