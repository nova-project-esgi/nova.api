package com.esgi.nova.adapters.web.authorization

import com.esgi.nova.ports.provided.enums.Role
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import org.slf4j.LoggerFactory

class RoleAuthorization internal constructor(config: Configuration) {
    val log = LoggerFactory.getLogger(RoleAuthorization::class.java)

    constructor(provider: RoleBasedAuthorizer) : this(Configuration(provider))

    private var config = config.copy()

    class Configuration internal constructor(var provider: RoleBasedAuthorizer) {
        internal fun copy(): Configuration = Configuration(provider)
    }

    companion object Feature : ApplicationFeature<ApplicationCallPipeline, RoleBasedAuthorizer, RoleAuthorization> {
        private val authorizationPhase = PipelinePhase("authorization")
        override val key: AttributeKey<RoleAuthorization> = AttributeKey("RoleAuthorization")

        @io.ktor.util.KtorExperimentalAPI
        override fun install(
            pipeline: ApplicationCallPipeline,
            configure: RoleBasedAuthorizer.() -> Unit
        ): RoleAuthorization {
            val configuration = RoleBasedAuthorizer().apply(configure)
            return RoleAuthorization(configuration)
        }
    }

    fun interceptPipeline(pipeline: ApplicationCallPipeline, roles: Set<Role>) {
        pipeline.insertPhaseAfter(
            ApplicationCallPipeline.Features,
            authorizationPhase
        )
        pipeline.intercept(authorizationPhase) {
            val call = call
            config.provider.authorizationFunction(call, roles).fold({
                log.debug(
                    "Responding unauthorized because of error",
                    it
                )
                call.respond(HttpStatusCode.Forbidden, "Permission is denied")
                finish()
            }, { return@intercept })
        }
    }
}