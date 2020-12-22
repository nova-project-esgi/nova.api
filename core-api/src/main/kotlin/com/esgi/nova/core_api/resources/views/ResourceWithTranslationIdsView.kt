package com.esgi.nova.core_api.resources.views

import java.util.*

data class ResourceWithTranslationIdsView(val id: UUID, val translationIds: List<UUID>) {
}