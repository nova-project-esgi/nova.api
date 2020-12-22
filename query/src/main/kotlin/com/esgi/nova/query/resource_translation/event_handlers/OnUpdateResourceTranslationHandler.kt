package com.esgi.nova.query.resource_translation.event_handlers

import com.esgi.nova.core_api.resource_translation.events.UpdatedResourceTranslationEvent
import com.esgi.nova.query.resource_translation.ResourceTranslationId
import com.esgi.nova.query.resource_translation.ResourceTranslationRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class OnUpdateResourceTranslationHandler constructor(
    private val resourceTranslationRepository: ResourceTranslationRepository
) {
    @EventHandler
    fun on(event: UpdatedResourceTranslationEvent) {
        resourceTranslationRepository.findByIdOrNull(
            ResourceTranslationId(
                resourceId = event.resourceId.toUUID(),
                languageId = event.translationId.toUUID()
            )
        )?.let { translation ->
            translation.name = event.name
            resourceTranslationRepository.saveAndFlush(translation)
        }
    }
}