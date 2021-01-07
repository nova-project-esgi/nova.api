package com.esgi.nova.core_api.languages.queries

import com.esgi.nova.core_api.languages.LanguageIdentifier

data class AllLanguagesExistsByIdsQuery(val languageIds: List<LanguageIdentifier>) {
}