package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.LanguageMapper
import com.esgi.nova.adapters.exposed.repositories.LanguageRepository
import com.esgi.nova.ports.common.constants.MultiLanguage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.language.LanguageCmdDto
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import com.esgi.nova.ports.required.ILanguagePersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class LanguagePersistence @Inject constructor(
    private val dbContext: DatabaseContext,
    private val languageRepository: LanguageRepository,
    private val languageMapper: LanguageMapper
) : ILanguagePersistence {
    override fun getAllTotalByCodes(
        pagination: IPagination,
        code: String,
        subCode: String?
    ): ITotalCollection<LanguageDto> =
        dbContext.connectAndExec {
            val totalCollection = languageRepository.getAllTotalByCodes(DatabasePagination(pagination), code, subCode)
            TotalCollection(
                totalCollection.total,
                languageMapper.toDtos(
                    totalCollection
                )
            )
        }

    override fun getAllByCodes(code: String, subCode: String?): List<LanguageDto> =
        dbContext.connectAndExec { languageMapper.toDtos(languageRepository.getAllByCodes(code, subCode).toList()) }

    override fun getOneByCodes(code: String, subCode: String?): LanguageDto? = dbContext.connectAndExec {
        languageRepository.getOneByCodes(code, subCode)?.let { language -> languageMapper.toDto(language) }
    }

    override fun getAll(): Collection<LanguageDto> = dbContext.connectAndExec {
        languageMapper.toDtos(languageRepository.getAll().toList())
    }

    override fun create(element: LanguageCmdDto): LanguageDto? =
        dbContext.connectAndExec { languageMapper.toDto(languageRepository.create(element)) }

    override fun getOne(id: UUID): LanguageDto? =
        dbContext.connectAndExec { languageRepository.getOne(id)?.let { language -> languageMapper.toDto(language) } }

    override fun getAllTotal(pagination: IPagination): ITotalCollection<LanguageDto> = dbContext.connectAndExec {
        val totalCollection = languageRepository.getAllTotal(DatabasePagination(pagination))
        TotalCollection(
            totalCollection.total,
            languageMapper.toDtos(
                totalCollection
            )
        )
    }
    override fun getDefault() = dbContext.connectAndExec{
        languageRepository.getOneByCodes(MultiLanguage.DEFAULT_CODE, null).let {language ->
            languageMapper.toDto(language)
        }
    }

    override fun updateOne(element: LanguageCmdDto, id: UUID): LanguageDto? = dbContext.connectAndExec {
        languageRepository.updateOne(id, element)?.let { language -> languageMapper.toDto(language) }
    }


}