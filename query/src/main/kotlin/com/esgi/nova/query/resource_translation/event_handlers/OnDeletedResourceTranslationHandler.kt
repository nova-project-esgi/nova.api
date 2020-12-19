package com.esgi.nova.query.resource_translation.event_handlers

import com.esgi.nova.core_api.resource_translation.events.DeletedResourceTranslationEvent
import com.esgi.nova.query.resource_translation.ResourceTranslationId
import com.esgi.nova.query.resource_translation.ResourceTranslationRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component
import java.util.*

@Component
open class OnDeletedResourceTranslationHandler constructor(
    private val resourceTranslationRepository: ResourceTranslationRepository
) {
    @EventHandler
    fun on(event: DeletedResourceTranslationEvent) {
        resourceTranslationRepository.deleteById(
            ResourceTranslationId(
                resourceId = UUID.fromString(event.id.resourceId),
                languageId = UUID.fromString(event.id.languageId)
            )
        )
    }
}