package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.LanguageMapper
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
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
    dbContext: DatabaseContext,
    override val repository: LanguageRepository,
    mapper: LanguageMapper
) : BasePersistence<UUID, LanguageCmdDto, LanguageEntity, LanguageDto>(repository, mapper, dbContext),
    ILanguagePersistence {
    override fun getAllTotalByCodes(
        pagination: IPagination,
        code: String,
        subCode: String?
    ): ITotalCollection<LanguageDto> =
        dbContext.connectAndExec {
            val totalCollection = repository.getAllTotalByCodes(DatabasePagination(pagination), code, subCode)
            TotalCollection(
                totalCollection.total,
                mapper.toDtos(
                    totalCollection
                )
            )
        }

    override fun getAllByCodes(code: String, subCode: String?): Collection<LanguageDto> =
        dbContext.connectAndExec { mapper.toDtos(repository.getAllByCodes(code, subCode).toList()) }

    override fun getOneByCodes(code: String, subCode: String?): LanguageDto? = dbContext.connectAndExec {
        repository.getOneByCodes(code, subCode)?.let { language -> mapper.toDto(language) }
    }
    override fun getDefault() = dbContext.connectAndExec {
        repository.getOneByCodes(MultiLanguage.DEFAULT_CODE, null).let { language ->
            mapper.toDto(language)
        }
    }

}