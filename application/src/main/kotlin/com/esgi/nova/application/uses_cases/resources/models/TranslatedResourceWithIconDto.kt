package com.esgi.nova.application.uses_cases.resources.models

import com.esgi.nova.application.pagination.Link
import java.util.*

data class TranslatedResourceWithIconDto(
    val id: UUID, val language: String, val name: String, val iconUrl: Link
)