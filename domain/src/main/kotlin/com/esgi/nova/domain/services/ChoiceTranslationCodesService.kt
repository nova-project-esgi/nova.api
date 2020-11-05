package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import com.esgi.nova.ports.provided.services.IChoiceTranslationCodesService
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.required.IChoiceTranslationPersistence
import com.google.inject.Inject

class ChoiceTranslationCodesService @Inject constructor(
    private val languageService: ILanguageService,
    private val choiceTranslationPersistence: IChoiceTranslationPersistence
) : IChoiceTranslationCodesService {

    override fun create(element: ChoiceTranslationCmdDto<String>): ChoiceTranslationDto? {
        languageService.getOneByCodes(element.language)?.let { l ->
            return choiceTranslationPersistence.create(
                ChoiceTranslationCmdDto(
                    element.title,
                    element.description,
                    element.choiceId,
                    l.id
                )
            )
        }
        return null
    }

    override fun updateOne(
        element: ChoiceTranslationCmdDto<String>,
        id: ChoiceTranslationKey<String>
    ): ChoiceTranslationDto? {
        languageService.getOneByCodes(id.language)?.let { l ->
            return choiceTranslationPersistence.updateOne(
                ChoiceTranslationCmdDto(
                    element.title,
                    element.description,
                    element.choiceId,
                    l.id
                ),
                ChoiceTranslationKey(id.choiceId, l.id)
            )
        }
        return null
    }

    override fun getOne(id: ChoiceTranslationKey<String>): ChoiceTranslationDto? {
        languageService.getOneByCodes(id.language)?.let { l ->
            return choiceTranslationPersistence.getOne(ChoiceTranslationKey(id.choiceId, l.id))
        }
        return null
    }
}