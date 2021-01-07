package com.esgi.nova.core_api.resources.commands

import com.esgi.nova.core_api.resources.ResourceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateResourceCommand(@TargetAggregateIdentifier val resourceId: ResourceIdentifier) {
}



