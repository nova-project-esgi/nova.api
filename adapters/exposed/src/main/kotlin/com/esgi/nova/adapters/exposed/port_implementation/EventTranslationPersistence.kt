package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.EventTranslationMapper
import com.esgi.nova.adapters.exposed.repositories.EventTranslationRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationKey
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationLanguageIdCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.required.IEventTranslationPersistence
import com.esgi.nova.ports.required.ILanguagePersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class EventTranslationPersistence @Inject constructor(
    private val dbContext: DatabaseContext,
    private val eventTranslationRepository: EventTranslationRepository,
    private val eventTranslationMapper: EventTranslationMapper,
    private val languagePersistence: ILanguagePersistence
) : IEventTranslationPersistence {


    override fun getTotalByLanguages(
        pagination: IPagination,
        languageIds: List<UUID>
    ): ITotalCollection<EventTranslationDto> = dbContext.connectAndExec {
        val totalCollection =
            eventTranslationRepository.getTotalByLanguages(DatabasePagination(pagination), languageIds)
        TotalCollection(
            totalCollection.total,
            eventTranslationMapper.toDtos(
                totalCollection
            )
        )
    }

    override fun getAll(): Collection<EventTranslationDto> =
        dbContext.connectAndExec {
            eventTranslationMapper.toDtos(
                eventTranslationRepository.getAll().toList()
            )
        }

    override fun create(element: EventTranslationLanguageIdCmdDto): EventTranslationDto? =
        dbContext.connectAndExec { eventTranslationMapper.toDto(eventTranslationRepository.create(element)) }

    override fun getAllTotal(pagination: IPagination): ITotalCollection<EventTranslationDto> =
        dbContext.connectAndExec {
            val totalCollection = eventTranslationRepository.getAllTotal(DatabasePagination(pagination))
            TotalCollection(
                totalCollection.total,
                eventTranslationMapper.toDtos(
                    totalCollection
                )
            )
        }

    override fun getOne(id: EventTranslationKey): EventTranslationDto? =
        dbContext.connectAndExec {
            eventTranslationRepository.getOne(id)
                ?.let { eventTranslation -> eventTranslationMapper.toDto(eventTranslation) }
        }
    override fun getAllByEventIdAndLanguageId(eventId: UUID, languageId: UUID) = dbContext.connectAndExec {
        eventTranslationMapper.toDtos(eventTranslationRepository.getAllByEventIdAndLanguageId(eventId, languageId))
    }

    override fun getOneDefault(eventId: UUID) = dbContext.connectAndExec {
        languagePersistence.getDefault()?.let { languageDto ->
            eventTranslationMapper.toDto(
                eventTranslationRepository.getOne(
                    EventTranslationKey(
                        eventId,
                        languageDto.id
                    )
                )
            )
        }
    }

}