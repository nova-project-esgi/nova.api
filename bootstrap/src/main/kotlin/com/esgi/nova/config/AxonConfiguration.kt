package com.esgi.nova.config

import org.axonframework.common.jdbc.PersistenceExceptionResolver
import org.axonframework.common.jpa.EntityManagerProvider
import org.axonframework.common.transaction.TransactionManager
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine
import org.axonframework.serialization.Serializer
import org.axonframework.spring.config.AxonConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

//@Configuration
//open class Test(){
//
//
//
//}
//@ApplicationScoped
@Configuration
open class AxonConfiguration @Autowired constructor(private val configurer: EventProcessingConfigurer) {

    @PostConstruct
    fun registerErrorHandling() {
        configurer.usingSubscribingEventProcessors()
    }
//    @Bean
//    open fun eventStore(storageEngine: EventStorageEngine, configuration: AxonConfiguration): EmbeddedEventStore? {
//        return EmbeddedEventStore.builder()
//                .storageEngine(storageEngine)
//                .messageMonitor(configuration.messageMonitor(EventStore::class.java,
//                        "eventStore"))
//                .build();
//    }
//
//    @Bean
//    open fun storageEngine(defaultSerializer: Serializer,
//                           persistenceExceptionResolver: PersistenceExceptionResolver,
//                           @Qualifier("eventSerializer") eventSerializer: Serializer,
//                           configuration: AxonConfiguration,
//                           entityManagerProvider: EntityManagerProvider,
//                           transactionManager: TransactionManager): EventStorageEngine? {
//        return JpaEventStorageEngine.builder()
//                .snapshotSerializer(defaultSerializer)
//                .upcasterChain(configuration.upcasterChain())
//                .persistenceExceptionResolver(persistenceExceptionResolver)
//                .eventSerializer(eventSerializer)
//                .entityManagerProvider(entityManagerProvider)
//                .transactionManager(transactionManager)
//                .build();
//    }

//    // The Event store `EmbeddedEventStore` delegates actual storage and retrieval of events to an `EventStorageEngine`.
//    @Bean
//    open fun eventStore(storageEngine: EventStorageEngine?, configuration: AxonConfiguration): EmbeddedEventStore? {
//        return EmbeddedEventStore.builder()
//                .storageEngine(storageEngine)
//                .messageMonitor(configuration.messageMonitor(EventStore::class.java, "eventStore"))
//                .build()
//    }
//    @Bean
//    open fun eventStorageEngine(connectionProvider: ConnectionProvider): JdbcEventStorageEngine? {
//        return JdbcEventStorageEngine.builder()
//                .connectionProvider(connectionProvider)
//                .transactionManager(NoTransactionManager.INSTANCE)
//                .build()
//    }
}

