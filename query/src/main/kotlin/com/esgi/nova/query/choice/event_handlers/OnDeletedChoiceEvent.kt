package com.esgi.nova.query.choice.event_handlers

import com.esgi.nova.core_api.choices.events.DeletedChoiceEvent
import com.esgi.nova.query.choice.ChoiceRepository
import com.esgi.nova.query.event.EventRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class  OnDeletedChoiceEvent constructor(private val choiceRepository: ChoiceRepository){

    @EventHandler
    fun on(event: DeletedChoiceEvent){
        choiceRepository.deleteById(event.choiceId.toUUID())
    }
}