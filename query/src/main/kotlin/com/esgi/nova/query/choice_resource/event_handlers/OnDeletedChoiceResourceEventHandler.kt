package com.esgi.nova.query.choice_resource.event_handlers

import com.esgi.nova.core_api.choices.events.DeletedChoiceResourceEvent
import com.esgi.nova.query.choice_resource.ChoiceResourceId
import com.esgi.nova.query.choice_resource.ChoiceResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnDeletedChoiceResourceEventHandler constructor(
    private val choiceResourceRepository: ChoiceResourceRepository
) {
    @EventHandler
    fun on(event: DeletedChoiceResourceEvent) {
        this.choiceResourceRepository.deleteById(
            ChoiceResourceId(
                choiceId = event.choiceId.toUUID(), resourceId = event.choiceResourceId.toUUID()
            )
        )
    }
}