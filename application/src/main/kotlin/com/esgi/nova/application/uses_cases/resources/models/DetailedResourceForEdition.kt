package com.esgi.nova.application.uses_cases.resources.models

data class DetailedResourceForEdition(
    val translations: List<ResourceTranslationForEdition>,
    val difficulties: List<ResourceDifficultyEdition>
) {
}