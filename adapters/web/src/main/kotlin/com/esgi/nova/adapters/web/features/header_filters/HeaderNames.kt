package com.esgi.nova.adapters.web.features.header_filters

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import org.slf4j.LoggerFactory

class HeaderNames internal constructor(config: Configuration) {
    val log = LoggerFactory.getLogger(HeaderNames::class.java)

    constructor(provider: HeaderNamesFilter) : this(Configuration(provider))

    private var config = config.copy()

    class Configuration internal constructor(var provider: HeaderNamesFilter) {
        internal fun copy(): Configuration = Configuration(provider)
    }

    companion object Feature : ApplicationFeature<ApplicationCallPipeline, HeaderNamesFilter, HeaderNames> {
        private val filterPhase = PipelinePhase("headerNamesFilter")
        override val key: AttributeKey<HeaderNames> = AttributeKey("HeaderNamesFilter")

        @io.ktor.util.KtorExperimentalAPI
        override fun install(
            pipeline: ApplicationCallPipeline,
            configure: HeaderNamesFilter.() -> Unit
        ): HeaderNames {
            val configuration = HeaderNamesFilter().apply(configure)
            return HeaderNames(configuration)
        }
    }

    fun interceptPipeline(pipeline: ApplicationCallPipeline, headerNames: Set<String>) {
        pipeline.insertPhaseAfter(
            ApplicationCallPipeline.Features,
            filterPhase
        )
        pipeline.intercept(filterPhase) {
            val call = call
            config.provider.filterFunction(call, headerNames).fold({
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