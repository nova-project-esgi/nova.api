package com.esgi.nova.adapters.exposed.models

import com.esgi.nova.adapters.exposed.tables.Language
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class LanguageEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<LanguageEntity>(Language)

    var code by Language.code
    var subCode by Language.subCode
}