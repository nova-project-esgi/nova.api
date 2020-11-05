package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey

interface IChoiceTranslationCodesService : IGetOne<ChoiceTranslationKey<String>, ChoiceTranslationDto>,
    ICreate<ChoiceTranslationCmdDto<String>, ChoiceTranslationDto>,
    IUpdateOne<ChoiceTranslationCmdDto<String>, ChoiceTranslationKey<String>, ChoiceTranslationDto> {
}