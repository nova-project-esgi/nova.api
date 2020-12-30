package com.esgi.nova.web.content_negociation


object CustomMediaType {
    object Application {
        const val ChoiceTranslation  = "application/vnd+nova.choice_translation+json"
        const val ResourceWithTranslations  = "application/vnd+nova.resource_translations+json"
        const val ResourceName  = "application/vnd+nova.resource_name+json"
        const val EventTranslation  = "application/vnd+nova.event_translation+json"
        const val EventTitle  = "application/vnd+nova.event_title+json"
        const val ChoiceWithResource  = "application/vnd+nova.choice_with_resources+json"
        const val ChoiceNavigation  = "application/vnd+nova.choice_navigation+json"
        const val DetailedEvent  = "application/vnd+nova.detailed_event+json"
        const val TranslatedChoice  = "application/vnd+nova.translated_choice+json"
        const val TranslatedChoiceResource  = "application/vnd+nova.translated_choice_resource+json"
        const val TranslatedResource  = "application/vnd+nova.translated_resource+json"
        const val LanguageWithAvailableActions = "application/vnd+nova.language_with_available_actions+json"
    }
}
