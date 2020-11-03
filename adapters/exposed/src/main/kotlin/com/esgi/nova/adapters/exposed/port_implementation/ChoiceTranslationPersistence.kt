package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.ChoiceTranslationMapper
import com.esgi.nova.adapters.exposed.repositories.ChoiceTranslationRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.ChoiceTranslationKey
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationLanguageIdCmdDto
import com.esgi.nova.ports.required.IChoiceTranslationPersistence
import com.esgi.nova.ports.required.ILanguagePersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class ChoiceTranslationPersistence @Inject constructor(
    private val dbContext: DatabaseContext,
    private val choiceTranslationRepository: ChoiceTranslationRepository,
    private val choiceTranslationMapper: ChoiceTranslationMapper,
    private val languagePersistence: ILanguagePersistence
) : IChoiceTranslationPersistence {
    override fun getAll(): Collection<ChoiceTranslationDto> =
        dbContext.connectAndExec {
            choiceTranslationMapper.toDtos(
                choiceTranslationRepository.getAll().toList()
            )
        }

    override fun create(element: ChoiceTranslationLanguageIdCmdDto): ChoiceTranslationDto? =
        dbContext.connectAndExec { choiceTranslationMapper.toDto(choiceTranslationRepository.create(element)) }

    override fun getAllTotal(pagination: IPagination): ITotalCollection<ChoiceTranslationDto> =
        dbContext.connectAndExec {
            val totalCollection = choiceTranslationRepository.getAllTotal(DatabasePagination(pagination))
            TotalCollection(
                totalCollection.total,
                choiceTranslationMapper.toDtos(
                    totalCollection
                )
            )
        }

    override fun getOne(id: ChoiceTranslationKey): ChoiceTranslationDto? =
        dbContext.connectAndExec {
            choiceTranslationRepository.getOne(id)
                ?.let { choiceTranslation -> choiceTranslationMapper.toDto(choiceTranslation) }
        }


    override fun getTotalByLanguages(
        pagination: IPagination,
        languageIds: List<UUID>
    ): ITotalCollection<ChoiceTranslationDto> = dbContext.connectAndExec {
        val totalCollection =
            choiceTranslationRepository.getTotalByLanguages(DatabasePagination(pagination), languageIds)
        TotalCollection(
            totalCollection.total,
            choiceTranslationMapper.toDtos(
                totalCollection
            )
        )
    }

    override fun getAllByChoiceIdAndLanguageId(choiceId: UUID, languageId: UUID): Collection<ChoiceTranslationDto> =
        dbContext.connectAndExec {
            choiceTranslationMapper.toDtos(
                choiceTranslationRepository.getAllByChoiceIdAndLanguageId(
                    choiceId,
                    languageId
                ).toList()
            )
        }

    override fun getAllByChoiceIdsAndLanguageId(
        choiceIds: List<UUID>,
        languageId: UUID
    ): Collection<ChoiceTranslationDto> = dbContext.connectAndExec {
        choiceTranslationMapper.toDtos(
            choiceTranslationRepository.getAllByChoiceIdsAndLanguageId(choiceIds, languageId).toList()
        )
    }

    override fun getAllDefaultByChoiceIds(choiceIds: List<UUID>): Collection<ChoiceTranslationDto> {
        languagePersistence.getDefault()?.let { language ->
            return getAllByChoiceIdsAndLanguageId(choiceIds, language.id)
        }
        return listOf()
    }

    override fun getOneDefault(choiceId: UUID) = dbContext.connectAndExec {
        languagePersistence.getDefault()?.let { languageDto ->
            choiceTranslationMapper.toDto(
                choiceTranslationRepository.getOne(
                    ChoiceTranslationKey(
                        choiceId,
                        languageDto.id
                    )
                )
            )
        }
    }
}