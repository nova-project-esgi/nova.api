package nova.api.adapters.db.model

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.*

object ChoiceResource: Table() {
    val choice: Column<EntityID<UUID>> = reference("choice_id", Choice)
    val resource: Column<EntityID<UUID>> = reference("resource_id", Resource)
    val changeValue: Column<Int> = integer("change_value")
}