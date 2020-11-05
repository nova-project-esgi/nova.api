package com.esgi.nova.domain.services

import com.esgi.nova.common.extensions.mergeDiff
import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.services.IChoiceTranslationService
import com.esgi.nova.ports.required.IChoiceTranslationPersistence
import com.google.inject.Inject
import java.util.*

class ChoiceTranslationService @Inject constructor(
    private val choiceTranslationPersistence: IChoiceTranslationPersistence,
) : IChoiceTranslationService {


    override fun getAll(): Collection<ChoiceTranslationDto> = choiceTranslationPersistence.getAll()

    override fun getOne(id: ChoiceTranslationKey<UUID>): ChoiceTranslationDto? = choiceTranslationPersistence.getOne(id)

    override fun create(element: ChoiceTranslationCmdDto<UUID>): ChoiceTranslationDto? =
        choiceTranslationPersistence.create(element)

    override fun getPage(pagination: IPagination): IPage<ChoiceTranslationDto> =
        choiceTranslationPersistence.getAllTotal(pagination).toStaticPage(pagination)

    override fun updateOne(
        element: ChoiceTranslationCmdDto<UUID>,
        id: ChoiceTranslationKey<UUID>
    ): ChoiceTranslationDto? = choiceTranslationPersistence.updateOne(element, id)


    override fun getOneOrDefault(id: ChoiceTranslationKey<UUID>) = choiceTranslationPersistence.getOne(id)
        ?: choiceTranslationPersistence.getOneDefault(id.choiceId)

    override fun getAllByChoiceIdsAndLanguageIdWithDefaults(
        choiceIds: List<UUID>,
        languageId: UUID
    ): Collection<ChoiceTranslationDto> {
        val choiceTranslations =
            choiceTranslationPersistence.getAllByChoiceIdsAndLanguageId(choiceIds, languageId).toMutableList()
        val defaultChoiceTranslations = choiceTranslationPersistence.getAllDefaultByChoiceIds(choiceIds)
        if (defaultChoiceTranslations.size > choiceTranslations.size) {
            choiceTranslations.mergeDiff(
                defaultChoiceTranslations
            ) { translation, defaultTranslation -> defaultTranslation.choice.id == translation.choice.id }
        }
        return choiceTranslations
    }
}