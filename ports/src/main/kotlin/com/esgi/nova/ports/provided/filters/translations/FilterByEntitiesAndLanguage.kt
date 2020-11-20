package com.esgi.nova.ports.provided.filters.translations

import com.esgi.nova.ports.provided.dtos.ITranslation

data class FilterByEntitiesAndLanguage<E,L>(val entities: Collection<E>, override var language: L): ITranslation<L>