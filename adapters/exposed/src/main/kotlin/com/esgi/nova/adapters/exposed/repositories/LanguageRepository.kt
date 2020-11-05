package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.adapters.exposed.tables.Language
import com.esgi.nova.ports.provided.dtos.language.LanguageCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class LanguageRepository {
    fun getAll(): SizedIterable<LanguageEntity> = transaction { LanguageEntity.all() }
    fun getOne(id: UUID): LanguageEntity? = transaction { LanguageEntity.findById(id) }
    fun getOneByCodes(code: String, subCode: String?): LanguageEntity? {
        val languages = getAllByCodes(code, subCode)
        if (subCode == null) {
            return languages.firstOrNull { l -> l.code == code }
        }
        return languages.firstOrNull()
    }

    fun create(language: LanguageCmdDto): LanguageEntity? = transaction {
        LanguageEntity.new {
            code = language.code
            subCode = language.subCode
        }
    }

    fun getAllTotal(pagination: DatabasePagination): ITotalCollection<LanguageEntity> = transaction {
        val elements = LanguageEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    fun getAllTotalByCodes(
        pagination: DatabasePagination,
        code: String,
        subCode: String?
    ): ITotalCollection<LanguageEntity> = transaction {
        val elements = getAllByCodes(code, subCode)
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    fun getAllByCodes(code: String, subCode: String?) = transaction {
        if (subCode != null) {
            LanguageEntity.find { (Language.code eq code) and (Language.subCode eq subCode) }
        } else {
            LanguageEntity.find { Language.code eq code }
        }
    }


    fun updateOne(id: UUID, language: LanguageCmdDto) = transaction { getOne(id)?.also { languageEntity ->
        languageEntity.code = language.code
        languageEntity.subCode = language.subCode
    } }

}