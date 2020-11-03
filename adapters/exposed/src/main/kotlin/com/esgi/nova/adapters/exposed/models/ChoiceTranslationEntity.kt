package com.esgi.nova.adapters.exposed.models

import com.esgi.nova.adapters.exposed.tables.ChoiceTranslation
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class ChoiceTranslationEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ChoiceTranslationEntity>(ChoiceTranslation)

    var title by ChoiceTranslation.title
    var description by ChoiceTranslation.description
    var choice by ChoiceEntity referencedOn ChoiceTranslation.choice
    var language by LanguageEntity referencedOn ChoiceTranslation.language
}