package com.esgi.nova.ports.provided.dtos.resource

import com.esgi.nova.ports.provided.dtos.resource_translation.IResourceTranslation

class TranslatedResourceCmdDto(override var name: String) : IResourceTranslation {
}