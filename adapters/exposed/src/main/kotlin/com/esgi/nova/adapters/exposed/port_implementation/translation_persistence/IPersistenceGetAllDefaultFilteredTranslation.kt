package com.esgi.nova.adapters.exposed.port_implementation.translation_persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.IGetAllIterableFiltered
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.ports.common.IGetAllFiltered
import com.esgi.nova.ports.provided.dtos.ITranslation
import com.esgi.nova.ports.required.ILanguagePersistence
import java.util.*

interface IPersistenceGetAllDefaultFilteredTranslation<Filter, FilterWithLanguage: ITranslation<UUID>, OutputDto, OutputEntity>: IGetAllFiltered<Filter, OutputDto> {
    val languagePersistence: ILanguagePersistence
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext

    fun toFilterWithLanguage(filter: Filter, language: UUID): FilterWithLanguage
    fun getAllFiltered(repository: IGetAllIterableFiltered<FilterWithLanguage, OutputEntity>, filter: Filter): Collection<OutputDto> = dbContext.connectAndExec {
        val res = mutableListOf<OutputDto>()
        languagePersistence.getDefault()?.let { languageDto ->
            res += mapper.toDtos(
                    repository.getAllFiltered(
                            toFilterWithLanguage(filter, languageDto.id)
                    ).toList()
            )
        }
        res
    }
}