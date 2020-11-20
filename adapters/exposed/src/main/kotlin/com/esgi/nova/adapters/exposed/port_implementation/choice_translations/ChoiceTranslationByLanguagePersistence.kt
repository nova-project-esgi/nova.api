package com.esgi.nova.adapters.exposed.port_implementation.choice_translations

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.mappers.ChoiceTranslationMapper
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
import com.esgi.nova.adapters.exposed.port_implementation.persistence.IPersistenceGetAllById
import com.esgi.nova.adapters.exposed.repositories.choice_translations.ChoiceTranslationByLanguageRepository
import com.esgi.nova.ports.common.IGetAllById
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import java.util.*

class ChoiceTranslationByLanguagePersistence(override val repository: ChoiceTranslationByLanguageRepository,
                                             mapper: ChoiceTranslationMapper, dbContext: DatabaseContext) :
        BasePersistence<UUID, ChoiceTranslationCmdDto<UUID>, ChoiceTranslationEntity, ChoiceTranslationDto>(repository, mapper, dbContext),
        IPersistenceGetAllById<UUID, ChoiceTranslationDto, ChoiceTranslationEntity>
{


}