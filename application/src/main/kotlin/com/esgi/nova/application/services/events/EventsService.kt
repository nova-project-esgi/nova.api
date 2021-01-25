package com.esgi.nova.application.services.events

import com.esgi.nova.application.extensions.queryPage
import com.esgi.nova.application.pagination.Link
import com.esgi.nova.application.pagination.Relation
import com.esgi.nova.application.services.events.exceptions.ChoiceWithoutResourcesException
import com.esgi.nova.application.services.events.exceptions.EventWithoutChoicesException
import com.esgi.nova.application.services.events.exceptions.NoDefaultLanguageChoiceTranslationException
import com.esgi.nova.application.services.events.exceptions.NoDefaultLanguageEventTranslationException
import com.esgi.nova.application.services.events.models.DetailedChoiceForEdition
import com.esgi.nova.application.services.events.models.DetailedChoiceForUpdate
import com.esgi.nova.application.services.events.models.DetailedEventForEdition
import com.esgi.nova.application.services.events.models.TranslatedEventsWithBackgroundDto
import com.esgi.nova.application.services.files.FileService
import com.esgi.nova.application.services.languages.LanguagesService
import com.esgi.nova.application.services.resources.exceptions.ResourceIconNotFoundException
import com.esgi.nova.common.extensions.extractFileName
import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.choices.commands.CreateChoiceCommand
import com.esgi.nova.core_api.choices.commands.CreateChoiceResourceCommand
import com.esgi.nova.core_api.choices.commands.CreateChoiceTranslationCommand
import com.esgi.nova.core_api.choices.commands.UpdateChoiceCommand
import com.esgi.nova.core_api.choices.dtos.ChoiceResourceEditionDto
import com.esgi.nova.core_api.choices.dtos.ChoiceTranslationEditionDto
import com.esgi.nova.core_api.events.EventIdentifier
import com.esgi.nova.core_api.events.commands.*
import com.esgi.nova.core_api.events.queries.*
import com.esgi.nova.core_api.events.views.*
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.resources.ResourceIdentifier
import com.esgi.nova.files.infra.UploadSettings
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
open class EventsService(
    private val queryGateway: QueryGateway,
    private val commandGateway: CommandGateway,
    private val fileService: FileService,
    private val languageUsesCases: LanguagesService,
    private val uploadSettings: UploadSettings
) {


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
        createChoicesForEvent(eventId, *event.choices.toTypedArray())
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
        upsertChoices(id, event)
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


    private fun upsertChoices(eventId: UUID, event: DetailedEventForEdition<DetailedChoiceForUpdate>) {
        event.choices.forEach {
            if (it.id != null) {
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
            } else {
                it.id = createChoicesForEvent(EventIdentifier(eventId.toString()), it).firstOrNull()
            }

        }
    }

    private fun createChoicesForEvent(
        eventId: EventIdentifier,
        vararg choices: DetailedChoiceForEdition
    ): List<UUID> {
        val ids = mutableListOf<UUID>()
        choices.forEach { choice ->
            val choiceId = commandGateway.sendAndWait<ChoiceIdentifier>(
                CreateChoiceCommand(
                    choiceId = ChoiceIdentifier(),
                    eventId = eventId
                )
            )
            ids += choiceId.toUUID()
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
        return ids
    }


    private fun <Choice : DetailedChoiceForEdition> validateEventEdition(event: DetailedEventForEdition<Choice>) {
        val defaultLanguageId = languageUsesCases.getDefaultLanguageId();
        if (event.translations.none { t -> t.languageId == defaultLanguageId }) {
            throw NoDefaultLanguageEventTranslationException()
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
            throw NoDefaultLanguageChoiceTranslationException()
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
            uploadSettings.events,
            background.originalFilename?.replace(background.extractFileName(), id.toString())
        )
    }

    fun getEventBackground(id: UUID): Resource {
        try {
            return fileService.loadFileAsResource(uploadSettings.events, id.toString())
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

    fun loadAllStandardEventsByLanguage(
        language: String,
        backgroundBaseUrl: String
    ): List<TranslatedEventsWithBackgroundDto> {
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
                isDaily = e.isDaily,
                backgroundUrl = Link(Relation.ASSET, "$backgroundBaseUrl/${e.id}/background", HttpMethod.GET)
            )
        }
    }

    fun loadOneStandardEventsByLanguage(
        language: String,
        eventId: UUID,
        backgroundBaseUrl: String
    ): TranslatedEventsWithBackgroundDto? {
        return queryGateway.query<TranslatedEventView?, FindOneTranslatedEventByLanguageAndId>(
            FindOneTranslatedEventByLanguageAndId(
                language = language,
                eventId = EventIdentifier(eventId.toString())
            )
        ).join()?.let { e ->
            TranslatedEventsWithBackgroundDto(
                id = e.id,
                language = e.language,
                description = e.description,
                title = e.title,
                choices = e.choices,
                isDaily = e.isDaily,
                backgroundUrl = Link(Relation.ASSET, "$backgroundBaseUrl/${e.id}/background", HttpMethod.GET)
            )
        }
    }


}

