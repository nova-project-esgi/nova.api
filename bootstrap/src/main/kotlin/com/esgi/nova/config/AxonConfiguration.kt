package com.esgi.nova.config

import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.*
import org.axonframework.eventhandling.ListenerInvocationErrorHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct


@Configuration
class AxonConfig @Autowired constructor(private val configurer: EventProcessingConfigurer) {

    @PostConstruct
    fun registerErrorHandling() {
        configurer.usingSubscribingEventProcessors()
    }
}
