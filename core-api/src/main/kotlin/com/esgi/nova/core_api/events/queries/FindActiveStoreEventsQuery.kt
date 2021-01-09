package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.events.EventIdentifier

data class FindActiveStoreEventsQuery(val ids: List<EventIdentifier>)