package com.esgi.nova.core_api.languages.queries

import com.esgi.nova.core_api.languages.LanguageIdentifier
import java.util.*

data class AllLanguagesExistsByIdsQuery(val languageIds: List<LanguageIdentifier>) {
}