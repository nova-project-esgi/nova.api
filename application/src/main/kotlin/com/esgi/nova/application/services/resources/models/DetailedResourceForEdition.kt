package com.esgi.nova.application.services.resources.models

data class DetailedResourceForEdition(
    val translations: List<ResourceTranslationForEdition>,
    val difficulties: List<ResourceDifficultyEdition>
) {
}