package com.esgi.nova.core_api.resources.events

import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import java.io.Serializable

data class CreatedResourceEvent(
    val id: ResourceIdentifier
) : Serializable

