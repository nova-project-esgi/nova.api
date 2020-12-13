package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.pagination.IPagination


data class FindPaginatedEventTranslationByEventIdAndLanguageIdsQuery(override val page: Int, override val size: Int, val eventId: EventIdentifier, val languageIds: List<LanguageIdentifier>) : IPagination {

}
