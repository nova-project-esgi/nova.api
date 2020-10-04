package nova.api.adapters.db.model

import nova.api.adapters.common.StringLength
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

object Event: UUIDTable() {
    val title: Column<String> = varchar("title", StringLength.LONG_STRING)
    val description: Column<String> = text("description")
    val isDaily: Column<Boolean> = bool("is_daily")
    val isActive: Column<Boolean> = bool("is_active")
}