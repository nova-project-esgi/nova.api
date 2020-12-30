package com.esgi.nova.core_api.languages.events

import com.esgi.nova.core_api.languages.LanguageIdentifier
import java.io.Serializable

data class LanguageUpdateEvent(val languageId: LanguageIdentifier, val code: String, val subCode: String?, val isDefault: Boolean) : Serializable