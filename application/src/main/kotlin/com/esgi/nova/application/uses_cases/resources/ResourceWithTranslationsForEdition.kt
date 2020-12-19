package com.esgi.nova.application.uses_cases.resources

import org.springframework.web.multipart.MultipartFile

data class ResourceWithTranslationsForEdition(
    val translations: List<ResourceTranslationForEdition>
    ) {
}