package com.esgi.nova.core_api.resources.queries

import com.esgi.nova.core_api.resources.commands.ResourceIdentifier

data class CanDeleteResourceByIdQuery(val id: ResourceIdentifier) {
}