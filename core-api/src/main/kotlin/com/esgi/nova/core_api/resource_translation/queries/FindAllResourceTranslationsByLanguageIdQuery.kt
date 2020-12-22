package com.esgi.nova.core_api.resource_translation.queries

import com.esgi.nova.core_api.languages.LanguageIdentifier

data class FindAllResourceTranslationsByLanguageIdQuery(val languageId: LanguageIdentifier)