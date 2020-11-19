package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.EventTranslationMapper
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.port_implementation.translation_persistence.BaseTranslationPersistence
import com.esgi.nova.adapters.exposed.repositories.EventTranslationRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.required.IEventTranslationPersistence
import com.esgi.nova.ports.required.ILanguagePersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class EventTranslationPersistence @Inject constructor(
    dbContext: DatabaseContext,
    override val repository: EventTranslationRepository,
    mapper: EventTranslationMapper,
    private val languagePersistence: ILanguagePersistence
) : BaseTranslationPersistence<EventTranslationKey<UUID>, EventTranslationCmdDto<UUID>, EventTranslationEntity, EventTranslationDto>(
    repository,
    mapper,
    dbContext
), IEventTranslationPersistence {

    override fun getTotalByLanguages(
        pagination: IPagination,
        languageIds: List<UUID>
    ): ITotalCollection<EventTranslationDto> = dbContext.connectAndExec {
        val totalCollection =
            repository.getTotalByLanguages(DatabasePagination(pagination), languageIds)
        TotalCollection(
            totalCollection.total,
            mapper.toDtos(
                totalCollection
            )
        )
    }

    override fun getAllByEventIdAndLanguageId(eventId: UUID, languageId: UUID): Collection<EventTranslationDto> = dbContext.connectAndExec {
        mapper.toDtos(repository.getAllByEventIdAndLanguageId(eventId, languageId))
    }

    override fun getOneDefault(eventId: UUID) = dbContext.connectAndExec {
        languagePersistence.getDefault()?.let { languageDto ->
            mapper.toDto(
                repository.getOne(
                    EventTranslationKey<UUID>(
                        eventId,
                        languageDto.id
                    )
                )
            )
        }
    }

}