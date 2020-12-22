package com.esgi.nova.application.uses_cases.events

import com.esgi.nova.application.axon.queryPage
import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.choices.commands.CreateChoiceCommand
import com.esgi.nova.core_api.events.commands.CreateEventCommand
import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.events.queries.FindPaginatedDetailedEventsByConcatenatedCodeAndTitleQuery
import com.esgi.nova.core_api.events.queries.FindPaginatedEventTitlesByConcatenatedCodeAndTitleQuery
import com.esgi.nova.core_api.events.queries.FindPaginatedEventTranslationByEventIdAndLanguageIdsQuery
import com.esgi.nova.core_api.events.views.DetailedEventView
import com.esgi.nova.core_api.events.views.EventTranslationTitleView
import com.esgi.nova.core_api.events.views.EventTranslationView
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.pagination.PageBase
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.util.*

@Service
open class EventsUseCases(private val queryGateway: QueryGateway, private val commandGateway: CommandGateway) {

    open fun testCreateEvent(event: DetailedEventForEdition) {
        val eventId = commandGateway.sendAndWait<EventIdentifier>(
            CreateEventCommand(
                isDaily = true,
                isActive = true,
                eventId = EventIdentifier()
            )
        )
        val choiceId = commandGateway.sendAndWait<ChoiceIdentifier>(
            CreateChoiceCommand(
                choiceId = ChoiceIdentifier(),
                eventId = eventId
            )
        )

    }

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
    ): PageBase<DetailedEventView> {
        return queryGateway.queryPage<DetailedEventView, FindPaginatedDetailedEventsByConcatenatedCodeAndTitleQuery>(
            FindPaginatedDetailedEventsByConcatenatedCodeAndTitleQuery(page, size, concatenatedCode, title)
        )
            .join()

    }


}