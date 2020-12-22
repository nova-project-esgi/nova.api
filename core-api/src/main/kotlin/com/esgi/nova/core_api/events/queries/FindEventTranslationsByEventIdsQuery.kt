package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.event_translations.commands.EventTranslationIdentifier

data class FindEventTranslationsByEventIdsQuery(val ids: List<EventTranslationIdentifier>) {
}