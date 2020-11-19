package com.esgi.nova.domain.services.translation_service

import java.util.*

interface ICodeIdToUuidId<IdCode, IdUuid> {
    fun transformId(codeId: IdCode, languageId: UUID): IdUuid
}