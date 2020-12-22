package com.esgi.nova.core_api.resources.views

import java.util.*

data class ResourceWithTranslationsView(val id: UUID, val translations: List<ResourceTranslationViewWithLanguage>) {
}
