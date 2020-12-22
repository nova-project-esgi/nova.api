package com.esgi.nova.core_api.choices.views

import java.util.*

data class ChoiceView(
        val id: UUID,
        val eventId: UUID,
        val resourceIds: List<UUID>
) {
}
