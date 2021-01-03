package com.esgi.nova.web.io.input

import com.esgi.nova.application.uses_cases.resources.models.ResourceTranslationForEdition

data class ResourceWithTranslationsForEdition(val translations: List<ResourceTranslationForEdition>) {
}