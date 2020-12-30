package com.esgi.nova.query.choice_resource.event_handlers

import com.esgi.nova.core_api.choice_resource.events.UpdatedChoiceResourceEvent
import com.esgi.nova.query.choice_resource.ChoiceResourceId
import com.esgi.nova.query.choice_resource.ChoiceResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class OnUpdatedChoiceResourceHandler constructor(
    private val choiceResourceRepository: ChoiceResourceRepository
) {
    @EventHandler
    fun on(event: UpdatedChoiceResourceEvent) {
        this.choiceResourceRepository.findByIdOrNull(
            ChoiceResourceId(
                choiceId = event.choiceId.toUUID(), resourceId = event.choiceResourceId.toUUID()
            )
        )?.let { choice ->
            choice.changeValue = event.changeValue
            choiceResourceRepository.saveAndFlush(choice)
        }
    }
}

