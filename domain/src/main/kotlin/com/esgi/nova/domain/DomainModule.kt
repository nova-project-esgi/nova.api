package com.esgi.nova.domain

import com.esgi.nova.domain.services.*
import com.esgi.nova.ports.provided.services.*
import com.google.inject.AbstractModule

class DomainModule : AbstractModule() {
    override fun configure() {
        bind(IUserService::class.java).to(UserService::class.java)
        bind(IEventService::class.java).to(EventService::class.java)
        bind(IResourceService::class.java).to(ResourceService::class.java)
        bind(IChoiceService::class.java).to(ChoiceService::class.java)
        bind(IChoiceResourceService::class.java).to(ChoiceResourceService::class.java)
        bind(IGameService::class.java).to(GameService::class.java)
        bind(IGameEventService::class.java).to(GameEventService::class.java)
    }
}
