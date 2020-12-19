package com.esgi.nova.core_api.resource_translation.queries

import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import java.util.*

data class FindAllResourceTranslationsByResourceIdQuery(val id: ResourceIdentifier)
