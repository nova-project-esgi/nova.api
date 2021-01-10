package com.esgi.nova.languages.write

import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.languages.commands.CreateLanguageCommand
import com.esgi.nova.core_api.languages.commands.DeleteLanguageCommand
import com.esgi.nova.core_api.languages.commands.UpdateLanguageCommand
import com.esgi.nova.core_api.languages.commands.UpdateLanguageDefaultCommand
import com.esgi.nova.core_api.languages.events.LanguageCreatedEvent
import com.esgi.nova.core_api.languages.events.LanguageDeletedEvent
import com.esgi.nova.core_api.languages.events.LanguageUpdateEvent
import com.esgi.nova.core_api.languages.exceptions.LanguageCodesHasNotChangeException
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class LanguageAggregate constructor() {

    @AggregateIdentifier
    private lateinit var id: LanguageIdentifier
    private lateinit var code: String
    private var subCode: String? = null
    private var isDefault: Boolean = false

    val concatenatedCode: String
        get() {
            subCode?.let {
                return "${code}-${subCode}"
            }
            return code
        }

    @CommandHandler
    constructor(command: CreateLanguageCommand) : this() {
        AggregateLifecycle.apply(
            LanguageCreatedEvent(
                languageId = command.languageId,
                code = command.code,
                subCode = command.subCode
            )
        )
    }

    // Event handlers
    @EventSourcingHandler
    fun on(event: LanguageCreatedEvent) {
        id = event.languageId
        code = event.code
        subCode = event.subCode
    }

    @CommandHandler
    fun handle(cmd: UpdateLanguageDefaultCommand) {
        AggregateLifecycle.apply(
            LanguageUpdateEvent(
                languageId = id,
                code = code,
                subCode = subCode,
                isDefault = cmd.isDefault
            )
        )
    }

    @CommandHandler
    fun handle(command: UpdateLanguageCommand) {
        AggregateLifecycle.apply(
            LanguageUpdateEvent(
                languageId = command.languageId,
                code = command.code,
                subCode = command.subCode,
                isDefault = isDefault
            )
        )
    }

    @EventSourcingHandler
    fun on(event: LanguageUpdateEvent) {
        code = event.code
        subCode = event.subCode
        isDefault = event.isDefault
    }

    @CommandHandler
    fun handle(command: DeleteLanguageCommand) {
        AggregateLifecycle.apply(LanguageDeletedEvent(languageId = command.languageId))
    }

    @EventSourcingHandler
    fun on(event: LanguageDeletedEvent) {
        AggregateLifecycle.markDeleted()
    }


    fun validateCodesAreDifferent(testCode: String, testSubCode: String?) {
        if (testSubCode != null) {
            if (subCode == testSubCode && code == testCode) {
                throw LanguageCodesHasNotChangeException()
            }
        } else if (code == testCode) {
            throw LanguageCodesHasNotChangeException()
        }
    }
}

