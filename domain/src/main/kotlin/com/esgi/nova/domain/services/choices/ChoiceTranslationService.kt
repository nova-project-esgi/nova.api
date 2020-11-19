package com.esgi.nova.domain.services

import com.esgi.nova.common.extensions.mergeDiff
import com.esgi.nova.domain.services.service.BaseService
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.services.IChoiceTranslationService
import com.esgi.nova.ports.required.IChoiceTranslationPersistence
import com.google.inject.Inject
import java.util.*

class ChoiceTranslationService @Inject constructor(
        override val persistence: IChoiceTranslationPersistence,
) : BaseService<ChoiceTranslationKey<UUID>, ChoiceTranslationCmdDto<UUID>, ChoiceTranslationDto>(persistence), IChoiceTranslationService {

    override fun getOneOrDefault(id: ChoiceTranslationKey<UUID>) = persistence.getOne(id)
            ?: persistence.getOneDefault(id.entityId)

    override fun getAllByChoiceIdsAndLanguageIdWithDefaults(
            choiceIds: List<UUID>,
            languageId: UUID
    ): Collection<ChoiceTranslationDto> {
        val choiceTranslations =
                persistence.getAllByChoiceIdsAndLanguageId(choiceIds, languageId).toMutableList()
        val defaultChoiceTranslations = persistence.getAllDefaultByChoiceIds(choiceIds)
        if (defaultChoiceTranslations.size > choiceTranslations.size) {
            choiceTranslations.mergeDiff(
                    defaultChoiceTranslations
            ) { translation, defaultTranslation -> defaultTranslation.choice.id == translation.choice.id }
        }
        return choiceTranslations
    }
}