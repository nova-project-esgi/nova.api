package com.esgi.nova.core_api.languages.views

import java.util.*

data class LanguageView(val id: UUID, val code: String, val subCode: String?, val isDefault: Boolean)
