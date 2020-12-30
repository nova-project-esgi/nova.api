package com.esgi.nova.query.language

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LanguageRepository : JpaRepository<Language, UUID> {


    fun findAllByCodeStartingWithAndSubCodeStartingWith(code: String, subCode: String?, pageable: Pageable): Page<Language>
    fun findByCodeAndSubCode(code: String, subCode: String?): Language?
    fun findAllByCode(code: String, pageable: Pageable): Page<Language>
    fun findAllByIsDefault(isDefault: Boolean): List<Language>
}