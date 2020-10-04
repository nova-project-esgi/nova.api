package nova.api.adapters.db.model

import nova.api.adapters.common.StringLength
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

import java.util.*

object Resource:UUIDTable(){
    val name: Column<String> = varchar("name", StringLength.MEDIUM_STRING)
}