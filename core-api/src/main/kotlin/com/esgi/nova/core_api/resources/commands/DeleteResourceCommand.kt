package com.esgi.nova.core_api.resources.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteResourceCommand(@TargetAggregateIdentifier val resourceId: ResourceIdentifier) {
}