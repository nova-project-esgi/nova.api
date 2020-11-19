package com.esgi.nova.ports.required.choice_translations

import com.esgi.nova.ports.common.IGetAllFiltered
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.filters.languages.FilterByLanguageId

interface IChoiceTranslationByLanguagePersistence: IGetAllFiltered<FilterByLanguageId, ChoiceTranslationDto>
