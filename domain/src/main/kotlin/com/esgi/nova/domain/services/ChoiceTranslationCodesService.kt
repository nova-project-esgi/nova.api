package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import com.esgi.nova.ports.provided.services.IChoiceTranslationCodesService
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.required.IChoiceTranslationPersistence
import com.google.inject.Inject
import java.util.*

class ChoiceTranslationCodesService @Inject constructor(
    languageService: ILanguageService,
    override val persistence: IChoiceTranslationPersistence
) : BaseTranslationCodesService<
        ChoiceTranslationKey<String>,
        ChoiceTranslationKey<UUID>,
        ChoiceTranslationCmdDto<String>,
        ChoiceTranslationCmdDto<UUID>,
        ChoiceTranslationDto>(
    languageService,
    persistence
),
    IChoiceTranslationCodesService {

    override fun codeInputToUuidInput(
        codesInput: ChoiceTranslationCmdDto<String>,
        languageId: UUID
    ): ChoiceTranslationCmdDto<UUID> {
        return ChoiceTranslationCmdDto(
            codesInput.title,
            codesInput.description,
            codesInput.choiceId,
            languageId
        )
    }


    override fun codeIdToUuidId(codeId: ChoiceTranslationKey<String>, languageId: UUID): ChoiceTranslationKey<UUID> {
        return ChoiceTranslationKey(codeId.choiceId, languageId)
    }


}