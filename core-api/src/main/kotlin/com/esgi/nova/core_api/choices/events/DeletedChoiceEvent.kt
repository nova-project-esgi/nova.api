package com.esgi.nova.core_api.choices.events

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import java.io.Serializable

data class DeletedChoiceEvent(
    val choiceId: ChoiceIdentifier
) : Serializable