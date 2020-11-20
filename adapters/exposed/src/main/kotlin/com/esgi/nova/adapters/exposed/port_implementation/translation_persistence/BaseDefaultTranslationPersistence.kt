package com.esgi.nova.adapters.exposed.port_implementation.translation_persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.domain.IRepositoryByLanguage
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.ports.common.IGetAllFiltered
import com.esgi.nova.ports.provided.dtos.ITranslationEntityKey
import com.esgi.nova.ports.provided.filters.translations.FilterByEntitiesAndLanguage
import com.esgi.nova.ports.required.ICrudPersistence
import com.esgi.nova.ports.required.IGetAllDefaultByIds
import com.esgi.nova.ports.required.IGetOneDefault
import com.esgi.nova.ports.required.ILanguagePersistence
import java.util.*


abstract class BaseDefaultTranslationPersistence<Id : ITranslationEntityKey<UUID, UUID>, InputDto, OutputEntity, OutputDto>(
        open val repository: IRepository<Id, InputDto, OutputEntity>,
        val repositoryByLanguage: IRepositoryByLanguage<InputDto, OutputEntity>,
        val languagePersistence: ILanguagePersistence,
        val mapper: IDtoMapper<OutputEntity, OutputDto>,
        val dbContext: DatabaseContext
) :
        IGetOneDefault<UUID, OutputDto>, IGetAllDefaultByIds<UUID, OutputDto>,
        IGetAllFiltered<FilterByEntitiesAndLanguage<UUID, UUID>, OutputDto> {
    protected abstract fun toTranslationKey(entityId: UUID, languageId: UUID): Id
    override fun getOneDefault(id: UUID): OutputDto? = dbContext.connectAndExec {
        languagePersistence.getDefault()?.let { languageDto ->
            mapper.toDto(
                    repository.getOne(
                            toTranslationKey(id, languageDto.id)
                    )
            )
        }
    }
    override fun getAllFiltered(filter: FilterByEntitiesAndLanguage<UUID, UUID>): Collection<OutputDto> = dbContext.connectAndExec {
        mapper.toDtos(repositoryByLanguage.getAllFiltered(filter).toList())
    }

    override fun getAllDefaultByIds(ids: Collection<UUID>): Collection<OutputDto> {
        languagePersistence.getDefault()?.let { language ->
            return getAllFiltered(FilterByEntitiesAndLanguage(ids, language.id))
        }
        return listOf()
    }

}