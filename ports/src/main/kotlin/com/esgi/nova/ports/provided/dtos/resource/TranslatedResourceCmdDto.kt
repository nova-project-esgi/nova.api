package com.esgi.nova.ports.provided.dtos.resource

import com.esgi.nova.ports.provided.dtos.ITranslation
import com.esgi.nova.ports.provided.dtos.resource_translation.IResourceTranslation

class TranslatedResourceCmdDto(override var name: String, override var language: String) : IResourceTranslation, ITranslation<String> {
}