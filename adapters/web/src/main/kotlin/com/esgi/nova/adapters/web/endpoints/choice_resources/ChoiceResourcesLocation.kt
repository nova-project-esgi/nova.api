package com.esgi.nova.adapters.web.endpoints.choice_resources

import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/choices/{cId}/resources/{rId}")
data class ChoiceResourcesLocation(val cId: UUID, val rId: UUID) {
}