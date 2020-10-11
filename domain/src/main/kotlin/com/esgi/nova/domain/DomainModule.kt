package com.esgi.nova.domain

import com.esgi.nova.ports.provided.IUserService
import com.google.inject.AbstractModule

class DomainModule : AbstractModule() {
    override fun configure() {
        bind(IUserService::class.java).to(UserService::class.java)
    }
}
