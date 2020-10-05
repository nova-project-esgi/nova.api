package nova.api.domain

import com.google.inject.AbstractModule
import nova.api.ports.provided.IUserService

class DomainModule : AbstractModule() {
    override fun configure() {
        bind(IUserService::class.java).to(UserService::class.java)
    }
}
