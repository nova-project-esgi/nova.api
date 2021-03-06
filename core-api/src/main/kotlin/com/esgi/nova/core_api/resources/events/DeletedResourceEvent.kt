package com.esgi.nova.core_api.resources.events

import com.esgi.nova.core_api.resources.ResourceIdentifier
import java.io.Serializable

data class DeletedResourceEvent(
    val resourceId: ResourceIdentifier
) : Serializable

