package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.ChoiceTranslationMapper
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.repositories.ChoiceTranslationRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import com.esgi.nova.ports.required.IChoiceTranslationPersistence
import com.esgi.nova.ports.required.ICrudPersistence
import com.esgi.nova.ports.required.ILanguagePersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class ChoiceTranslationPersistence @Inject constructor(
    dbContext: DatabaseContext,
    override val repository: ChoiceTranslationRepository,
    mapper: ChoiceTranslationMapper,
    private val languagePersistence: ILanguagePersistence
) : BasePersistence<ChoiceTranslationKey<UUID>, ChoiceTranslationCmdDto<UUID>, ChoiceTranslationEntity, ChoiceTranslationDto>(
    repository,
    mapper,
    dbContext
) ,IChoiceTranslationPersistence {

    override fun getTotalByLanguages(
        pagination: IPagination,
        languageIds: List<UUID>
    ): ITotalCollection<ChoiceTranslationDto> = dbContext.connectAndExec {
        val totalCollection =
            repository.getTotalByLanguages(DatabasePagination(pagination), languageIds)
        TotalCollection(
            totalCollection.total,
            mapper.toDtos(
                totalCollection
            )
        )
    }

    override fun getAllByChoiceIdAndLanguageId(choiceId: UUID, languageId: UUID): Collection<ChoiceTranslationDto> =
        dbContext.connectAndExec {
            mapper.toDtos(
                repository.getAllByChoiceIdAndLanguageId(
                    choiceId,
                    languageId
                ).toList()
            )
        }

    override fun getAllByChoiceIdsAndLanguageId(
        choiceIds: List<UUID>,
        languageId: UUID
    ): Collection<ChoiceTranslationDto> = dbContext.connectAndExec {
        mapper.toDtos(
            repository.getAllByChoiceIdsAndLanguageId(choiceIds, languageId).toList()
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
            mapper.toDto(
                repository.getOne(
                    ChoiceTranslationKey(
                        choiceId,
                        languageDto.id
                    )
                )
            )
        }
    }
}