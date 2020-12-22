package com.esgi.nova.query.resource_translation

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ResourceTranslationRepository : JpaRepository<ResourceTranslation, ResourceTranslationId> {
    fun findAllByResourceId(resourceId: UUID): List<ResourceTranslation>
    fun findAllByNameStartingWithAndLanguageCodeStartingWith(
        name: String,
        code: String,
        page: Pageable
    ): Page<ResourceTranslation>

    fun findAllByNameStartingWithAndLanguageConcatenatedCodesStartingWith(
        name: String,
        concatenatedCode: String,
        page: Pageable
    ): Page<ResourceTranslation>

    fun findAllByNameStartingWithAndLanguageCodeStartingWithAndLanguageSubCodeStartingWith(
        name: String,
        code: String,
        subCode: String,
        page: Pageable
    ): Page<ResourceTranslation>

    fun findAllByLanguageId(languageId: UUID): List<ResourceTranslation>
}