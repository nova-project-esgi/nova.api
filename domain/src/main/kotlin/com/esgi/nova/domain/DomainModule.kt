package com.esgi.nova.domain

import com.esgi.nova.domain.services.*
import com.esgi.nova.domain.services.choices.*
import com.esgi.nova.domain.services.events.EventService
import com.esgi.nova.domain.services.events.EventTranslationCodesService
import com.esgi.nova.domain.services.events.EventTranslationService
import com.esgi.nova.domain.services.events.TranslatedEventService
import com.esgi.nova.domain.services.resources.ResourceService
import com.esgi.nova.domain.services.resources.ResourceTranslationCodesService
import com.esgi.nova.domain.services.resources.ResourceTranslationService
import com.esgi.nova.domain.services.resources.TranslatedResourceService
import com.esgi.nova.ports.provided.services.*
import com.esgi.nova.ports.provided.services.choices.IChoiceNavigationService
import com.esgi.nova.ports.provided.services.choices.IChoiceResourceService
import com.esgi.nova.ports.provided.services.choices.IChoiceService
import com.esgi.nova.ports.provided.services.choices.ITranslatedChoiceService
import com.esgi.nova.ports.provided.services.events.IEventService
import com.esgi.nova.ports.provided.services.events.IEventTranslationCodesService
import com.esgi.nova.ports.provided.services.events.IEventTranslationService
import com.esgi.nova.ports.provided.services.events.ITranslatedEventService
import com.esgi.nova.ports.provided.services.resources.IResourceService
import com.esgi.nova.ports.provided.services.resources.IResourceTranslationCodesService
import com.esgi.nova.ports.provided.services.resources.IResourceTranslationService
import com.esgi.nova.ports.provided.services.resources.ITranslatedResourceService
import com.google.inject.AbstractModule

class DomainModule : AbstractModule() {
    override fun configure() {
        bind(IChoiceNavigationService::class.java).to(ChoiceNavigationService::class.java)
        bind(IChoiceResourceService::class.java).to(ChoiceResourceService::class.java)
        bind(IChoiceService::class.java).to(ChoiceService::class.java)
        bind(IChoiceTranslationService::class.java).to(ChoiceTranslationService::class.java)
        bind(IChoiceTranslationCodesService::class.java).to(ChoiceTranslationCodesService::class.java)
        bind(IEventService::class.java).to(EventService::class.java)
        bind(IEventTranslationService::class.java).to(EventTranslationService::class.java)
        bind(IEventTranslationCodesService::class.java).to(EventTranslationCodesService::class.java)
        bind(IGameEventService::class.java).to(GameEventService::class.java)
        bind(IGameService::class.java).to(GameService::class.java)
        bind(ILanguageService::class.java).to(LanguageService::class.java)
        bind(IResourceService::class.java).to(ResourceService::class.java)
        bind(IResourceTranslationService::class.java).to(ResourceTranslationService::class.java)
        bind(IResourceTranslationCodesService::class.java).to(ResourceTranslationCodesService::class.java)
        bind(ITranslatedChoiceService::class.java).to(TranslatedChoiceService::class.java)
        bind(ITranslatedEventService::class.java).to(TranslatedEventService::class.java)
        bind(ITranslatedResourceService::class.java).to(TranslatedResourceService::class.java)
        bind(IUserService::class.java).to(UserService::class.java)
    }
}
