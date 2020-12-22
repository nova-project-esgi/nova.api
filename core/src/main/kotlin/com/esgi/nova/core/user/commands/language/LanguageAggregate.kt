package com.esgi.nova.core.user.commands.language

import com.esgi.nova.core_api.languages.commands.UpdateLanguageCommand
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.languages.events.LanguageCreatedEvent
import com.esgi.nova.core_api.languages.events.LanguageUpdateEvent
import com.esgi.nova.core_api.languages.exceptions.LanguageCodesHasNotChangeException
import com.esgi.nova.core_api.languages.commands.CreateLanguageCommand
import com.esgi.nova.core_api.languages.commands.DeleteLanguageCommand
import com.esgi.nova.core_api.languages.events.LanguageDeletedEvent
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

    val concatenatedCode: String
        get() {
            subCode?.let {
                return "${code}-${subCode}"
            }
            return code
        }

    @CommandHandler
    constructor(command: CreateLanguageCommand) : this() {
        AggregateLifecycle.apply(LanguageCreatedEvent(languageId = command.id, code = command.code, subCode = command.subCode))
    }


    @CommandHandler
    fun onUpdateLanguageCommand(command: UpdateLanguageCommand) {
        validateCodesAreDifferent(
                testCode = command.code,
                testSubCode = command.subCode)
        AggregateLifecycle.apply(LanguageUpdateEvent(languageId = command.languageId, code = command.code, subCode = command.subCode))
    }

    @CommandHandler
    fun onDeleteLanguageCommand(command: DeleteLanguageCommand) {
        AggregateLifecycle.apply(LanguageDeletedEvent(languageId = command.languageId))
    }

    // Event handlers
    @EventSourcingHandler
    fun onLanguageCreatedEvent(event: LanguageCreatedEvent) {
        id = event.languageId
        code = event.code
        subCode = event.subCode
    }

    @EventSourcingHandler
    fun onLanguageUpdatedEvent(event: LanguageUpdateEvent) {
        code = event.code
        subCode = event.subCode
    }
    @EventSourcingHandler
    fun onLanguageDeletedEvent(event: LanguageDeletedEvent) {
        AggregateLifecycle.markDeleted()
    }


    fun validateCodesAreDifferent(testCode: String, testSubCode: String?) {
        if(testSubCode != null ){
            if (subCode == testSubCode && code == testCode) {
                throw LanguageCodesHasNotChangeException()
            }
        }
        else if (code == testCode) {
            throw LanguageCodesHasNotChangeException()
        }
    }
}

