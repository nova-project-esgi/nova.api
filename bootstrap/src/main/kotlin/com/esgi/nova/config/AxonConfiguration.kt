package com.esgi.nova.config

import org.axonframework.common.jdbc.ConnectionProvider
import org.axonframework.common.transaction.NoTransactionManager
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct


@Configuration
open class AxonConfig @Autowired constructor(private val configurer: EventProcessingConfigurer) {

    @PostConstruct
    fun registerErrorHandling() {
        configurer.usingSubscribingEventProcessors()
    }

    @Bean
    open fun eventStorageEngine(connectionProvider: ConnectionProvider): JdbcEventStorageEngine? {
        return JdbcEventStorageEngine.builder()
                .connectionProvider(connectionProvider)
                .transactionManager(NoTransactionManager.INSTANCE)
                .build()
    }
}

