package com.esgi.nova.core_api.languages.queries.views

import java.util.*

data class LanguageViewWithAvailableActions(val id: UUID, val code: String, val subCode: String?, val canDelete: Boolean)