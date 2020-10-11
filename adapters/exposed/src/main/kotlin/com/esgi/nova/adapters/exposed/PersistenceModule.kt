package com.esgi.nova.adapters.exposed

import com.esgi.nova.adapters.exposed.mappers.UserMapper
import com.esgi.nova.adapters.exposed.port_implementation.UserPersistence
import com.esgi.nova.ports.required.IUserPersistence
import com.google.inject.AbstractModule
import org.jetbrains.exposed.sql.Database
import org.mapstruct.factory.Mappers

class PersistenceModule(
    databaseUrl: String,
    databaseDriver: String,
    databaseUsr: String,
    databasePwd: String
) : AbstractModule() {
    init {
        Database.connect(
            databaseUrl, databaseDriver,
            user = databaseUsr, password = databasePwd
        )
    }

    override fun configure() {

        bind(IUserPersistence::class.java).to(UserPersistence::class.java)
        bind(UserMapper::class.java).to(Mappers.getMapperClass(UserMapper::class.java))
    }


}