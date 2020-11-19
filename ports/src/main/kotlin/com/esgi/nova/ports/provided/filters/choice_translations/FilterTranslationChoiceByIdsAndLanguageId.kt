package com.esgi.nova.ports.provided.filters.choice_translations

import com.esgi.nova.ports.provided.dtos.ITranslation
import com.esgi.nova.ports.provided.filters.IFilterMultiple
import java.util.*

class FilterTranslationChoiceByIdsAndLanguageId (override val entities: List<UUID>, override var language: UUID): IFilterMultiple<UUID>, ITranslation<UUID>{
}