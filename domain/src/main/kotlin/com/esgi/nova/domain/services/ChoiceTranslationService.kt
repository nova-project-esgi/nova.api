package com.esgi.nova.domain.services

import com.esgi.nova.common.extensions.mergeDiff
import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.ChoiceTranslationKey
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationLanguageCodesCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationLanguageIdCmdDto
import com.esgi.nova.ports.provided.services.IChoiceTranslationService
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.required.IChoiceTranslationPersistence
import com.google.inject.Inject
import java.util.*

class ChoiceTranslationService @Inject constructor(
    private val choiceTranslationPersistence: IChoiceTranslationPersistence,
    private val languageService: ILanguageService
) : IChoiceTranslationService {
    override fun createWithCodes(choiceTranslation: ChoiceTranslationLanguageCodesCmdDto): ChoiceTranslationDto? {
        languageService.getOneByCodes(choiceTranslation.languageCodes)?.let { l ->
            return choiceTranslationPersistence.create(
                ChoiceTranslationLanguageIdCmdDto(
                    choiceTranslation.title,
                    choiceTranslation.description,
                    choiceTranslation.choiceId,
                    l.id
                )
            )
        }
        return null
    }

    override fun getOneWithCodes(choiceId: UUID, languageCodes: String): ChoiceTranslationDto? {
        languageService.getOneByCodes(languageCodes)?.let { l ->
            return choiceTranslationPersistence.getOne(ChoiceTranslationKey(choiceId, l.id))
        }
        return null
    }

    override fun getAll(): Collection<ChoiceTranslationDto> = choiceTranslationPersistence.getAll()

    override fun getOne(id: ChoiceTranslationKey): ChoiceTranslationDto? = choiceTranslationPersistence.getOne(id)

    override fun create(element: ChoiceTranslationLanguageIdCmdDto): ChoiceTranslationDto? =
        choiceTranslationPersistence.create(element)

    override fun getPage(pagination: IPagination): IPage<ChoiceTranslationDto> =
        choiceTranslationPersistence.getAllTotal(pagination).toStaticPage(pagination)

    override fun getOneOrDefault(id: ChoiceTranslationKey) = choiceTranslationPersistence.getOne(id)
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