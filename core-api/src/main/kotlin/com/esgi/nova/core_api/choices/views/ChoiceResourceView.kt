package com.esgi.nova.core_api.choices.views

import com.esgi.nova.core_api.resources.views.ResourceWithAvailableActionsView
import java.util.*

data class ChoiceResourceView(
    val choiceId: UUID,
    val changeValue: Int,
    val resource: ResourceWithAvailableActionsView
)
