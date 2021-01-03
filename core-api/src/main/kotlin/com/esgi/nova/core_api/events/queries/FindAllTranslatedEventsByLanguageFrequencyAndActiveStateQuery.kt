package com.esgi.nova.core_api.events.queries

data class FindAllTranslatedEventsByLanguageFrequencyAndActiveStateQuery(val language: String, val isActive: Boolean, val isDaily: Boolean)
