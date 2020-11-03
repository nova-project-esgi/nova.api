package com.esgi.nova.adapters.web.endpoints.resources

import io.ktor.locations.*
import java.util.*

@Location("/resources/{id}")
data class ResourceLocation(val id: UUID) {
}