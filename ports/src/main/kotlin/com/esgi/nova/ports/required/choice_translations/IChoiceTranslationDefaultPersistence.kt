package com.esgi.nova.ports.required.choice_translations

import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import java.util.*

interface IChoiceTranslationDefaultPersistence: IGetOne<UUID, ChoiceTranslationDto>
