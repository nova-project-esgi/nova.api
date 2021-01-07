package com.esgi.nova.core_api.languages.views

import java.util.*

data class LanguageViewWithAvailableActions(
    val id: UUID,
    val code: String,
    val subCode: String?,
    val isDefault: Boolean,
    val canDelete: Boolean,
    val canSetDefault: Boolean
)