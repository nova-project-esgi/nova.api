package com.esgi.nova.web.io.input

import com.esgi.nova.application.uses_cases.resources.ResourceTranslationForEdition

data class ResourceWithTranslationsForEdition(val translations: List<ResourceTranslationForEdition>) {
}