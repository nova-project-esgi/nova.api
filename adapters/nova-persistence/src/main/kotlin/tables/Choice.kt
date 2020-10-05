package tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object Choice : UUIDTable() {
    val title: Column<String> = varchar("title", StringLength.LONG_STRING)
    val description: Column<String> = text("description")
    val event: Column<EntityID<UUID>> = reference("event_id", Event)
}

