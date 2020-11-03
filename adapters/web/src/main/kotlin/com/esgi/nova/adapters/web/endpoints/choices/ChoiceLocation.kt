package com.esgi.nova.adapters.web.endpoints.choices

import io.ktor.locations.*
import java.util.*

@Location("/choices/{id}")
data class ChoiceLocation(val id: UUID) {
}