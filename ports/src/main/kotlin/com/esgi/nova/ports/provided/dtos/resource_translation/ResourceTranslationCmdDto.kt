package com.esgi.nova.ports.provided.dtos.resource_translation

import com.esgi.nova.ports.provided.dtos.ITranslation
import java.util.*

open class ResourceTranslationCmdDto<T>(override var name: String, var resourceId: UUID, override var language: T) : IResourceTranslation,
    ITranslation<T> {
}