package com.esgi.nova.application.uses_cases.events

import com.esgi.nova.application.axon.queryPage
import com.esgi.nova.application.pagination.Link
import com.esgi.nova.application.pagination.Relation
import com.esgi.nova.application.services.files.FileService
import com.esgi.nova.application.uses_cases.events.models.*
import com.esgi.nova.application.uses_cases.languages.LanguagesUseCases
import com.esgi.nova.application.uses_cases.resources.ResourceIconNotFoundException
import com.esgi.nova.common.extensions.extractFileName
import com.esgi.nova.core_api.choices.commands.CreateChoiceResourceCommand
import com.esgi.nova.core_api.choices.commands.CreateChoiceTranslationCommand
import com.esgi.nova.core_api.choices.exceptions.NoDefaultLanguageChoiceTranslation
import com.esgi.nova.core_api.choices.commands.*
import com.esgi.nova.core_api.choices.exceptions.ChoiceWithoutResourcesException
import com.esgi.nova.core_api.events.commands.CreateEventTranslationCommand
import com.esgi.nova.core_api.events.exceptions.NoDefaultLanguageEventTranslation
import com.esgi.nova.core_api.events.commands.*
import com.esgi.nova.core_api.events.exceptions.EventWithoutChoicesException
import com.esgi.nova.core_api.events.queries.*
import com.esgi.nova.core_api.events.views.*
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.languages.exceptions.DefaultLanguageNotFound
import com.esgi.nova.core_api.languages.queries.FindDefaultLanguageQuery
import com.esgi.nova.core_api.languages.queries.views.LanguageView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import io.netty.handler.codec.http.HttpMethod
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.extensions.kotlin.queryMany
import org.axonframework.queryhandling.QueryGateway
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.util.*

@Service
open class EventsUseCases(
    private val queryGateway: QueryGateway,
    private val commandGateway: CommandGateway,
    private val fileService: FileService,
    private val languageUsesCases: LanguagesUseCases
) {
    private val eventDir = "events"


    open fun getOneById(id: UUID): EventView {
        return queryGateway.query<EventView, FindOneEventByIdQuery>(FindOneEventByIdQuery(EventIdentifier(id.toString())))
            .join()
    }

    open fun createEvent(event: DetailedEventForEdition<DetailedChoiceForEdition>): UUID {
        validateEventEdition(event)
        val eventId = commandGateway.sendAndWait<EventIdentifier>(
            CreateEventCommand(
                isActive = event.isActive,
                isDaily = event.isDaily,
                eventId = EventIdentifier()
            )
        )
        createChoicesForEvent(event, eventId)
        event.translations.forEach { translation ->
            commandGateway.sendAndWait(
                CreateEventTranslationCommand(
                    eventId = eventId,
                    title = translation.title,
                    description = translation.description,
                    translationId = LanguageIdentifier(translation.languageId.toString())
                )
            )
        }
        return eventId.toUUID();
    }

    open fun updateEvent(id: UUID, event: DetailedEventForEdition<DetailedChoiceForUpdate>): UUID {
        validateEventEdition(event)
        updateChoices(event)
        commandGateway.sendAndWait<EventIdentifier>(
            UpdateEventCommand(
                isActive = event.isActive,
                isDaily = event.isDaily,
                eventId = EventIdentifier(id.toString()),
                translations = event.translations.map {
                    EventTranslationEditionDto(
                        translationId = LanguageIdentifier(it.languageId.toString()),
                        title = it.title,
                        description = it.description
                    )
                }, choiceIds = event.choices.map { c -> ChoiceIdentifier(c.id.toString()) }
            )
        )
        return id
    }


    private fun updateChoices(event: DetailedEventForEdition<DetailedChoiceForUpdate>) {
        event.choices.forEach {
            commandGateway.sendAndWait<ChoiceIdentifier>(UpdateChoiceCommand(
                choiceId = ChoiceIdentifier(it.id.toString()),
                translations = it.translations.map { t ->
                    ChoiceTranslationEditionDto(
                        translationId = LanguageIdentifier(
                            t.languageId.toString()
                        ), title = t.title, description = t.description
                    )
                },
                resources = it.resources.map { r ->
                    ChoiceResourceEditionDto(
                        choiceResourceId = ResourceIdentifier(r.resourceId.toString()),
                        changeValue = r.changeValue
                    )
                }
            ))
        }
    }

    private fun createChoicesForEvent(
        event: DetailedEventForEdition<DetailedChoiceForEdition>,
        eventId: EventIdentifier
    ) {
        event.choices.forEach { choice ->
            val choiceId = commandGateway.sendAndWait<ChoiceIdentifier>(
                CreateChoiceCommand(
                    choiceId = ChoiceIdentifier(),
                    eventId = eventId
                )
            )
            choice.translations.forEach { translation ->
                commandGateway.sendAndWait(
                    CreateChoiceTranslationCommand(
                        choiceId = choiceId,
                        translationId = LanguageIdentifier(translation.languageId.toString()),
                        description = translation.description,
                        title = translation.title
                    )
                )
            }
            choice.resources.forEach { resource ->
                commandGateway.sendAndWait(
                    CreateChoiceResourceCommand(
                        choiceId = choiceId,
                        choiceResourceId = ResourceIdentifier(resource.resourceId.toString()),
                        changeValue = resource.changeValue
                    )
                )
            }
        }
    }


    private fun <Choice : DetailedChoiceForEdition> validateEventEdition(event: DetailedEventForEdition<Choice>) {
        val defaultLanguageId = languageUsesCases.getDefaultLanguageId();
        if (event.translations.none { t -> t.languageId == defaultLanguageId }) {
            throw NoDefaultLanguageEventTranslation()
        }
        if (event.choices.isEmpty()) {
            throw EventWithoutChoicesException()
        }
        if (event.choices.any { c -> c.resources.isEmpty() }) {
            throw ChoiceWithoutResourcesException()
        }
        if (event.choices.any { c ->
                c.translations.none { t ->
                    t.languageId == defaultLanguageId
                }
            }) {
            throw NoDefaultLanguageChoiceTranslation()
        }
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

    fun setEventBackground(background: MultipartFile, id: UUID) {
        fileService.setFile(
            background,
            eventDir,
            background.originalFilename?.replace(background.extractFileName(), id.toString())
        )
    }

    fun getEventBackground(id: UUID): Resource {
        try {
            return fileService.loadFileAsResource(eventDir, id.toString())
        } catch (e: FileNotFoundException) {
            throw ResourceIconNotFoundException()
        }
    }

    fun deleteOneEventById(id: UUID) {
        commandGateway
            .sendAndWait<Any>(DeleteEventCommand(eventId = EventIdentifier(id.toString())))
    }

    fun getOneDetailedById(id: UUID): DetailedEventView {
        return queryGateway.query<DetailedEventView, FindOneDetailedEventByIdQuery>(
            FindOneDetailedEventByIdQuery(
                EventIdentifier(id.toString())
            )
        ).join()
    }

    fun loadAllStandardEventsByLanguage(language: String, backgroundBaseUrl: String): List<TranslatedEventsWithBackgroundDto> {
        return queryGateway.queryMany<TranslatedEventView, FindAllTranslatedEventsByLanguageFrequencyAndActiveStateQuery>(
            FindAllTranslatedEventsByLanguageFrequencyAndActiveStateQuery(
                language = language,
                isActive = true,
                isDaily = false
            )
        ).join().map { e ->
            TranslatedEventsWithBackgroundDto(
                id = e.id,
                language = e.language,
                description = e.description,
                title = e.title,
                choices = e.choices,
                backgroundUrl = Link(Relation.ASSET, "$backgroundBaseUrl/${e.id}/background", HttpMethod.GET)
            )
        }
    }


}

