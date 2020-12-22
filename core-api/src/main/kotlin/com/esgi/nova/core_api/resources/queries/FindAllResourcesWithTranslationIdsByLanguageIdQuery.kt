package com.esgi.nova.core_api.resources.queries

import com.esgi.nova.core_api.languages.LanguageIdentifier

data class FindAllResourcesWithTranslationIdsByLanguageIdQuery(
    val languageId: LanguageIdentifier
)