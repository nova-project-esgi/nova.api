package nova.api.adapters.db.model

import nova.api.adapters.common.StringLength
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.*

object User: UUIDTable() {
    val username: Column<String> = varchar("username", StringLength.LONG_STRING)
    val password: Column<String> = varchar("password", StringLength.LONG_STRING)
}