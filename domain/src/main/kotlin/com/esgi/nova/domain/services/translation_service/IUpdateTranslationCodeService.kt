package com.esgi.nova.domain.services.translation_service

import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.ITranslation
import com.esgi.nova.ports.provided.services.ILanguageService
import java.util.*

interface IUpdateTranslationCodeService<
        InputCode : ITranslation<String>,
        InputUuid : ITranslation<UUID>,
        IdCode : ITranslation<String>,
        IdUuid : ITranslation<UUID>,
        Output> : IUpdateOne<InputCode, IdCode, Output> {
    val persistence: IUpdateOne<InputUuid, IdUuid, Output>
    val languageService: ILanguageService
    val idMapper: ICodeIdToUuidId<IdCode, IdUuid>
    val inputMapper: ICodeInputToUuidInput<InputCode, InputUuid>

    override fun updateOne(
            element: InputCode,
            id: IdCode
    ): Output? {
        languageService.getOneByCodes(id.language)?.let { l ->
            return persistence.updateOne(
                    inputMapper.transformInput(element, l.id),
                    idMapper.transformId(id, l.id)
            )
        }
        return null
    }
}