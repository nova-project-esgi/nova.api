package com.esgi.nova.domain.services.choices

import com.esgi.nova.domain.services.translation_service.BaseTranslationCodesService
import com.esgi.nova.domain.services.translation_service.ICodeIdToUuidId
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

    override var codeMapper: ICodeIdToUuidId<ChoiceTranslationKey<String>, ChoiceTranslationKey<UUID>> = this
    override fun transformInput(
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


    override fun transformId(codeId: ChoiceTranslationKey<String>, languageId: UUID): ChoiceTranslationKey<UUID> {
        return ChoiceTranslationKey(codeId.entityId, languageId)
    }


    override fun getResumesByTitleAndLanguage(pagination: IPagination, title: String, codes: String): IPage<ChoiceTranslationResumeDto> {
        languageService.getOneByCodes(codes)?.let { languageDto ->
            val total =  persistence.getTotalByTitleAndLanguage(pagination, languageDto.id, title)
            return total.map { translation -> translation.toResume() }.toStaticPage(pagination, total.total.toInt())
        }
        return StaticPage.emptyPage(pagination)
    }
}