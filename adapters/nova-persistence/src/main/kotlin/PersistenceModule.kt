import com.google.inject.AbstractModule
import nova.api.ports.required.IUserPersistence
import port_implementation.UserPersistence

class PersistenceModule: AbstractModule() {
    override fun configure() {
        bind(IUserPersistence::class.java).to(UserPersistence::class.java)
    }
}