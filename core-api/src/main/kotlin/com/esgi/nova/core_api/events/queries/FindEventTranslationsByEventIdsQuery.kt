package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.events.commands.EventTranslationIdentifier

data class FindEventTranslationsByEventIdsQuery(val ids: List<EventTranslationIdentifier>) {
}