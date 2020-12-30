package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.languages.LanguageIdentifier


data class FindEventTranslationsByEventIdsQuery(val ids: List<LanguageIdentifier>) {
}