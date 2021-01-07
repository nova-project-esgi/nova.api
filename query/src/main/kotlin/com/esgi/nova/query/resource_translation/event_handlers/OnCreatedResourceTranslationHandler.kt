package com.esgi.nova.query.resource_translation.event_handlers

import com.esgi.nova.core_api.resources.events.CreatedResourceTranslationEvent
import com.esgi.nova.query.language.LanguageRepository
import com.esgi.nova.query.resource.ResourceRepository
import com.esgi.nova.query.resource_translation.ResourceTranslation
import com.esgi.nova.query.resource_translation.ResourceTranslationId
import com.esgi.nova.query.resource_translation.ResourceTranslationRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnCreatedResourceTranslationHandler constructor(
    private val resourceTranslationRepository: ResourceTranslationRepository,
    private val resourceRepository: ResourceRepository,
    private val languageRepository: LanguageRepository
) {
    @EventHandler
    fun on(event: CreatedResourceTranslationEvent) {
        val resource = resourceRepository.getOne(event.resourceId.toUUID())
        val language = languageRepository.getOne(event.translationId.toUUID())
        try {
            resourceTranslationRepository.save(
                ResourceTranslation(
                    ResourceTranslationId(
                        resourceId = resource.id,
                        languageId = language.id
                    ),
                    event.name, resource, language
                )
            )
        } catch (e: Exception) {
            throw e
        }


    }
}