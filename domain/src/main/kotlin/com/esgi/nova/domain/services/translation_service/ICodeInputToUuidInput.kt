package com.esgi.nova.domain.services.translation_service

import java.util.*

interface ICodeInputToUuidInput<InputCode, InputUuid> {
    fun transformInput(codesInput: InputCode, languageId: UUID): InputUuid
}