package com.esgi.nova.core_api.resources.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateResourceCommand(@TargetAggregateIdentifier val resourceId: ResourceIdentifier, val translations: List<TranslationEditionDto>) {
}