package com.esgi.nova.config

import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.*
import org.axonframework.eventhandling.ListenerInvocationErrorHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import java.lang.Exception
import javax.annotation.PostConstruct


@Configuration
class AxonConfig @Autowired constructor(private val configurer: EventProcessingConfigurer) {

    @PostConstruct
    fun registerErrorHandling() {
//        configurer.registerTrackingEventProcessor()
        configurer.usingSubscribingEventProcessors()
//        configurer.registerDefaultListenerInvocationErrorHandler {config ->
//            ListenerInvocationErrorHandler(config)
//        }
//        configurer.registerDefaultErrorHandler {config ->
//            NovaErrorHandler(config)
//        }
    }
}

class ListenerInvocationErrorHandler  constructor(private val config: org.axonframework.config.Configuration) :
    ListenerInvocationErrorHandler {
    override fun onError(exception: Exception?, event: EventMessage<*>?, eventHandler: EventMessageHandler?) {
        println(exception)
    }

}

class NovaErrorHandler constructor(private val config: org.axonframework.config.Configuration ): ErrorHandler{
    override fun handleError(errorContext: ErrorContext?) {
        println(errorContext)
//        errorContext?.let { it.failedEveLanguageAggregate::class.javants().forEach { e -> e. } }
    }

}