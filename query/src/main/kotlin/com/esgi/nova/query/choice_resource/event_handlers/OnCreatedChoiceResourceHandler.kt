package com.esgi.nova.query.choice_resource.event_handlers

import com.esgi.nova.core_api.choices.events.CreatedChoiceResourceEvent
import com.esgi.nova.query.choice.ChoiceRepository
import com.esgi.nova.query.choice_resource.ChoiceResource
import com.esgi.nova.query.choice_resource.ChoiceResourceId
import com.esgi.nova.query.choice_resource.ChoiceResourceRepository
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnCreatedChoiceResourceHandler constructor(
    private val choiceResourceRepository: ChoiceResourceRepository,
    private val resourceRepository: ResourceRepository,
    private val choiceRepository: ChoiceRepository
) {
    @EventHandler
    fun on(event: CreatedChoiceResourceEvent) {
        val choice = choiceRepository.getOne(event.choiceId.toUUID())
        val resource = resourceRepository.getOne(event.choiceResourceId.toUUID())
        try {
            choiceResourceRepository.save(
                ChoiceResource(
                    ChoiceResourceId(
                        resourceId = resource.id,
                        choiceId = choice.id
                    ),
                    changeValue = event.changeValue,
                    resource = resource,
                    choice = choice
                )
            )
        } catch (e: Exception) {
            throw e
        }
    }
}