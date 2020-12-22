package com.esgi.nova.core.user.commands.resources

import com.esgi.nova.core.user.commands.resource_translation.ResourceTranslationEntity
import com.esgi.nova.core_api.resource_translation.commands.CanDeleteResourceTranslationCommand
import com.esgi.nova.core_api.resource_translation.commands.CreateResourceTranslationCommand
import com.esgi.nova.core_api.resource_translation.commands.DeleteResourceTranslationCommand
import com.esgi.nova.core_api.resource_translation.commands.ResourceTranslationIdentifier
import com.esgi.nova.core_api.resource_translation.events.CreatedResourceTranslationEvent
import com.esgi.nova.core_api.resource_translation.events.DeletedResourceTranslationEvent
import com.esgi.nova.core_api.resource_translation.events.UpdatedResourceTranslationEvent
import com.esgi.nova.core_api.resources.commands.CreateResourceCommand
import com.esgi.nova.core_api.resources.commands.DeleteResourceCommand
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import com.esgi.nova.core_api.resources.commands.UpdateResourceCommand
import com.esgi.nova.core_api.resources.events.CreatedResourceEvent
import com.esgi.nova.core_api.resources.events.DeletedResourceEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ResourceAggregate constructor() {
    @AggregateIdentifier
    private lateinit var id: ResourceIdentifier

    @AggregateMember
    private var translations: MutableList<ResourceTranslationEntity> = mutableListOf()

    @CommandHandler
    constructor(cmd: CreateResourceCommand) : this() {
        AggregateLifecycle.apply(CreatedResourceEvent(resourceId = cmd.resourceId))
    }

    @EventSourcingHandler
    fun on(event: CreatedResourceEvent) {
        id = event.resourceId
    }

    @CommandHandler
    fun handle(cmd: UpdateResourceCommand) {
        val translationEditionIds = mutableListOf<ResourceTranslationIdentifier>()
        cmd.translations.forEach { translationEdition ->
            val translation =
                translations.firstOrNull { translation -> translationEdition.translationId == translation.id }
            if (translation == null) {
                AggregateLifecycle.apply(
                    CreatedResourceTranslationEvent(
                        resourceId = id,
                        translationId = translationEdition.translationId,
                        name = translationEdition.name
                    )
                )
            } else {
                translationEditionIds.add(translation.id)
                AggregateLifecycle.apply(
                    UpdatedResourceTranslationEvent(
                        resourceId = id,
                        translationId = translationEdition.translationId,
                        name = translationEdition.name
                    )
                )
            }
        }
        val translationDeleteIds = mutableListOf<ResourceTranslationIdentifier>()
        translations.forEach { translation ->
            if (translationEditionIds.none { translationId -> translation.id == translationId }) {
                translationDeleteIds += translation.id
            }
        }
        if (translationDeleteIds.size == this.translations.size && cmd.translations.isEmpty()) {
            throw ResourceTranslationMinimalSizeException()
        } else {
            translationDeleteIds.forEach { translationId ->
                AggregateLifecycle.apply(DeletedResourceTranslationEvent(resourceId = id, translationId = translationId))
            }
        }
    }

    @CommandHandler
    fun handle(cmd: CreateResourceTranslationCommand) {
        AggregateLifecycle.apply(
            CreatedResourceTranslationEvent(
                resourceId = cmd.id,
                translationId = cmd.translationId,
                name = cmd.name
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreatedResourceTranslationEvent) {
        this.translations.add(ResourceTranslationEntity(id = event.translationId, name = event.name))
    }


    @CommandHandler
    fun handle(command: DeleteResourceCommand) {
        AggregateLifecycle.apply(DeletedResourceEvent(resourceId = command.resourceId))
    }

    @EventSourcingHandler
    fun on(event: DeletedResourceEvent) {
        AggregateLifecycle.markDeleted();
    }

    @CommandHandler
    fun handle(command: DeleteResourceTranslationCommand) {
        if (translations.size == 1) {
            throw ResourceTranslationMinimalSizeException()
        }
        AggregateLifecycle.apply(
            DeletedResourceTranslationEvent(
                resourceId = command.id,
                translationId = command.translationId
            )
        )
    }

    @CommandHandler
    fun handle(command: CanDeleteResourceTranslationCommand){
        if(translations.size == 1){
            throw ResourceTranslationMinimalSizeException()
        }
    }

    @EventSourcingHandler
    fun on(event: DeletedResourceTranslationEvent) {
        this.translations.removeIf { it.id == event.translationId }
    }


}