package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.events.commands.EventIdentifier


data class EventExistsQuery(val id: EventIdentifier)
