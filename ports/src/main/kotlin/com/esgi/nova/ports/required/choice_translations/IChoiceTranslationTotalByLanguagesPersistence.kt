package com.esgi.nova.ports.required.choice_translations

import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.filters.languages.FilterByLanguageIds
import com.esgi.nova.ports.required.IGetAllTotalFiltered

interface IChoiceTranslationTotalByLanguagesPersistence: IGetAllTotalFiltered<FilterByLanguageIds, ChoiceTranslationDto>
