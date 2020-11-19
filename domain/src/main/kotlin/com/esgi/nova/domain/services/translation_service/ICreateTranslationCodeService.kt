package com.esgi.nova.domain.services.translation_service

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.provided.dtos.ITranslation
import com.esgi.nova.ports.provided.services.ILanguageService
import java.util.*

interface ICreateTranslationCodeService<InputCode : ITranslation<String>, InputUuid : ITranslation<UUID>, Output> : ICreate<InputCode, Output> {
    val persistence: ICreate<InputUuid, Output>
    val languageService: ILanguageService
    val inputMapper: ICodeInputToUuidInput<InputCode, InputUuid>
    override fun create(element: InputCode): Output? {
        languageService.getOneByCodes(element.language)?.let { l ->
            return persistence.create(inputMapper.transformInput(element, l.id))
        }
        return null
    }
}