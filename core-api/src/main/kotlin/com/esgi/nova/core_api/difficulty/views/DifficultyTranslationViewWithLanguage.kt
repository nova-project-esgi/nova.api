package com.esgi.nova.core_api.difficulty.views

import com.esgi.nova.core_api.languages.views.LanguageView

data class DifficultyTranslationViewWithLanguage(val name: String, val language: LanguageView) {
}