package com.esgi.nova.adapters.web.endpoints.translated_events

import com.esgi.nova.adapters.web.domain.pagination.PaginationDefault
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.ITranslation
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location("/events")
class TranslatedEventsLocation(
        override var language: String,
        override val page: Long = PaginationDefault.PAGE,
        override val size: Long = PaginationDefault.SIZE) : IPagination, ITranslation<String> {
}
