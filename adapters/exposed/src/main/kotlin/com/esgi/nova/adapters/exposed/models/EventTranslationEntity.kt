package com.esgi.nova.adapters.exposed.models

import com.esgi.nova.adapters.exposed.tables.EventTranslation
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class EventTranslationEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<EventTranslationEntity>(EventTranslation)

    private var _games: List<GameEntity>? = null
    var title by EventTranslation.title
    var description by EventTranslation.description
    var event by EventEntity referencedOn EventTranslation.event
    var language by LanguageEntity referencedOn EventTranslation.language
}