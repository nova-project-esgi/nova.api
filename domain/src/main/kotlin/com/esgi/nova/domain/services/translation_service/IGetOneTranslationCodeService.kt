package com.esgi.nova.domain.services.translation_service

import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.dtos.ITranslation
import com.esgi.nova.ports.provided.services.ILanguageService
import java.util.*

interface IGetOneTranslationCodeService<IdCode : ITranslation<String>, IdUuid : ITranslation<UUID>, Output> : IGetOne<IdCode, Output> {
    var codeMapper: ICodeIdToUuidId<IdCode, IdUuid>
    val languageService: ILanguageService
    val persistence: IGetOne<IdUuid, Output>
    override fun getOne(id: IdCode): Output? {
        languageService.getOneByCodes(id.language)?.let { l ->
            return persistence.getOne(codeMapper.transformId(id, l.id))
        }
        return null
    }
}