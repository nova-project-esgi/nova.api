package com.esgi.nova.adapters.exposed.port_implementation.translation_persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.dtos.ITranslationEntityKey
import com.esgi.nova.ports.required.ILanguagePersistence
import java.util.*

interface IPersistenceGetOneDefaultTranslation<Id, OutputDto, OutputEntity>: IGetOne<Id, OutputDto> {
    val languagePersistence: ILanguagePersistence
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext

    fun toTranslationEntityKey(id: Id, languageId: UUID): ITranslationEntityKey<Id, UUID>;
    fun getOne(repository: IGetOne<ITranslationEntityKey<Id, UUID>, OutputEntity>, id: Id): OutputDto? = dbContext.connectAndExec {
        languagePersistence.getDefault()?.let { languageDto ->
            mapper.toDto(
                    repository.getOne(
                           toTranslationEntityKey(id, languageDto.id)
                    )
            )
        }
    }
}
