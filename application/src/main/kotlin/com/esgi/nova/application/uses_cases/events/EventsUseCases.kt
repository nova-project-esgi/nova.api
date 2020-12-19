package com.esgi.nova.application.uses_cases.events

import com.esgi.nova.application.axon.queryPage
import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.events.queries.FindPaginatedEventTitlesByConcatenatedCodeAndTitleQuery
import com.esgi.nova.core_api.events.queries.FindPaginatedEventTranslationByEventIdAndLanguageIdsQuery
import com.esgi.nova.core_api.events.queries.FindPaginatedTranslatedEventByConcatenatedCodeAndTitleQuery
import com.esgi.nova.core_api.events.views.EventTranslationTitleView
import com.esgi.nova.core_api.events.views.EventTranslationView
import com.esgi.nova.core_api.events.views.TranslatedEventView
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.pagination.PageBase
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.command.Repository
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.util.*

@Service
open class  EventsUseCases(private val queryGateway: QueryGateway, private val commandGateway: CommandGateway) {

    open fun getPaginatedTranslatedEventTitlesByConcatenatedCodeAndTitle(
        page: Int,
        size: Int,
        concatenatedCode: String,
        title: String
    ): PageBase<EventTranslationTitleView> {
        return queryGateway.queryPage<EventTranslationTitleView, FindPaginatedEventTitlesByConcatenatedCodeAndTitleQuery>(
            FindPaginatedEventTitlesByConcatenatedCodeAndTitleQuery(page, size, concatenatedCode, title)
        ).join()
    }

    open fun getTranslationsByEventAndLanguages(
        id: UUID,
        page: Int,
        size: Int,
        languageIds: List<UUID>
    ): PageBase<EventTranslationView> {
        return queryGateway.queryPage<EventTranslationView, FindPaginatedEventTranslationByEventIdAndLanguageIdsQuery>(
            FindPaginatedEventTranslationByEventIdAndLanguageIdsQuery(
                page = page,
                size = size,
                eventId = EventIdentifier(id.toString()),
                languageIds = languageIds.map { LanguageIdentifier(it.toString()) }
            )
        )
            .join()

    }


    open fun getPaginatedTranslatedEventsByConcatenatedCodeAndTitle(
        page: Int,
        size: Int,
        concatenatedCode: String,
        title: String
    ): PageBase<TranslatedEventView> {
        return queryGateway.queryPage<TranslatedEventView, FindPaginatedTranslatedEventByConcatenatedCodeAndTitleQuery>(
            FindPaginatedTranslatedEventByConcatenatedCodeAndTitleQuery(page, size, concatenatedCode, title)
        )
            .join()

    }


}