package com.esgi.nova.core_api.resources.queries

import com.esgi.nova.core_api.languages.LanguageIdentifier

data class FindAllResourceTranslationsByLanguageIdQuery(val languageId: LanguageIdentifier)