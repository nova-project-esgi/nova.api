package com.esgi.nova.adapters.exposed.domain

import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.ports.provided.dtos.ITranslationEntityKey
import com.esgi.nova.ports.provided.filters.translations.FilterByEntitiesAndLanguage
import java.util.*

interface IRepositoryByLanguage<Input, Output> :
        IRepository<UUID, Input, Output>,
        IGetAllIterableById<UUID, Output>,
        IGetAllTotalById<UUID, Output>,
        IGetAllTotalByIds<UUID, Output>,
        IGetAllIterableFiltered<FilterByEntitiesAndLanguage<UUID, UUID>, Output> {
}