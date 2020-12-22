package com.esgi.nova.query.choice.event_handlers

import com.esgi.nova.core_api.choices.events.CreatedChoiceEvent
import com.esgi.nova.query.choice.Choice
import com.esgi.nova.query.choice.ChoiceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class  OnCreatedChoiceEvent constructor(private val choiceRepository: ChoiceRepository){

    @EventHandler
    fun on(event: CreatedChoiceEvent){
        choiceRepository.saveAndFlush(Choice(id = event.choiceId.toUUID()))
    }
}