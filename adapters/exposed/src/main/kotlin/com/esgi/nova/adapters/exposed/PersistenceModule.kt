package com.esgi.nova.adapters.exposed

import com.esgi.nova.adapters.exposed.mappers.*
import com.esgi.nova.adapters.exposed.port_implementation.*
import com.esgi.nova.ports.required.*
import com.google.inject.AbstractModule
import org.mapstruct.factory.Mappers

class PersistenceModule(
    databaseUrl: String,
    databaseDriver: String,
    databaseUsr: String,
    databasePwd: String,
) : AbstractModule() {
    val dbContext: DatabaseContext = DatabaseContext(databaseUrl, databaseDriver, databaseUsr, databasePwd)

    override fun configure() {

        //PERSISTENCE: DatabaseContext
        bind(IUserPersistence::class.java).to(UserPersistence::class.java)
        bind(IGamePersistence::class.java).to(GamePersistence::class.java)
        bind(IChoicePersistence::class.java).to(ChoicePersistence::class.java)
        bind(IEventPersistence::class.java).to(EventPersistence::class.java)
        bind(IChoiceResourcePersistence::class.java).to(ChoiceResourcePersistence::class.java)
        bind(IGameEventPersistence::class.java).to(GameEventPersistence::class.java)
        bind(IResourcePersistence::class.java).to(ResourcePersistence::class.java)
        bind(IResourceTranslationPersistence::class.java).to(ResourceTranslationPersistence::class.java)
        bind(IEventTranslationPersistence::class.java).to(EventTranslationPersistence::class.java)
        bind(IChoiceTranslationPersistence::class.java).to(ChoiceTranslationPersistence::class.java)
        bind(ILanguagePersistence::class.java).to(LanguagePersistence::class.java)

        //MAPPERS
        bind(UserMapper::class.java).to(Mappers.getMapperClass(UserMapper::class.java))
        bind(GameMapper::class.java).to(Mappers.getMapperClass(GameMapper::class.java))
        bind(ChoiceMapper::class.java).to(Mappers.getMapperClass(ChoiceMapper::class.java))
        bind(ResourceMapper::class.java).to(Mappers.getMapperClass(ResourceMapper::class.java))
        bind(ChoiceResourceMapper::class.java).to(Mappers.getMapperClass(ChoiceResourceMapper::class.java))
        bind(GameEventMapper::class.java).to(Mappers.getMapperClass(GameEventMapper::class.java))
        bind(EventMapper::class.java).to(Mappers.getMapperClass(EventMapper::class.java))
        bind(EntityMapper::class.java).to(Mappers.getMapperClass(EntityMapper::class.java))
        bind(EventTranslationMapper::class.java).to(Mappers.getMapperClass(EventTranslationMapper::class.java))
        bind(ChoiceTranslationMapper::class.java).to(Mappers.getMapperClass(ChoiceTranslationMapper::class.java))
        bind(ResourceTranslationMapper::class.java).to(Mappers.getMapperClass(ResourceTranslationMapper::class.java))
        bind(LanguageMapper::class.java).to(Mappers.getMapperClass(LanguageMapper::class.java))

        bind(DatabaseContext::class.java).toInstance(dbContext)
    }


}