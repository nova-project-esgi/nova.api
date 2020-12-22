package com.esgi.nova.query.resource

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ResourceRepository : JpaRepository<Resource, UUID> {

    fun findByResourceTranslationsNameStartingWith(name: String): List<Resource>
    fun findAllByResourceTranslationsNameStartingWith(
        name: String,
        page: Pageable
    ): Page<Resource>

    //    fun findAllByResourceTranslationsContainingNameStartingWithAndResourceTranslationsContainingLanguageConcatenatedCodesStartingWith(
    fun findAllByResourceTranslationsNameStartingWithAndResourceTranslationsLanguageConcatenatedCodesStartingWith(
        name: String,
        concatenatedCode: String,
        page: Pageable
    ): Page<Resource>

    fun findAllByResourceTranslationsLanguageId(
        languageId: UUID
    ): List<Resource>
}