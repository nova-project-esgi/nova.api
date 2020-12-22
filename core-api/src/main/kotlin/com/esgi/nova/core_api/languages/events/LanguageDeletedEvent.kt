package com.esgi.nova.core_api.languages.events

import com.esgi.nova.core_api.languages.LanguageIdentifier
import java.io.Serializable

data class LanguageDeletedEvent(val languageId: LanguageIdentifier) : Serializable