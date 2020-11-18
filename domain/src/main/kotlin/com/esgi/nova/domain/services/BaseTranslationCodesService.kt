package com.esgi.nova.domain.services

import com.esgi.nova.domain.domain.StaticPage
import com.esgi.nova.domain.extensions.toPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.ITranslation
import com.esgi.nova.ports.provided.services.ICrudService
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.provided.services.ITranslationService
import com.esgi.nova.ports.required.ICrudPersistence
import com.esgi.nova.ports.required.ITranslationPersistence
import java.util.*

abstract class BaseTranslationCodesService<IdCode : ITranslation<String>, IdUuid : ITranslation<UUID>, InputCode : ITranslation<String>, InputUuid : ITranslation<UUID>, Output>(
    protected val languageService: ILanguageService,
    protected open val persistence: ITranslationPersistence<IdUuid, InputUuid, Output>
) : ITranslationService<IdCode, InputCode, Output> {
    protected abstract fun codeInputToUuidInput(codesInput: InputCode, languageId: UUID): InputUuid;
    protected abstract fun codeIdToUuidId(codeId: IdCode, languageId: UUID): IdUuid;

    override fun create(element: InputCode): Output? {
        languageService.getOneByCodes(element.language)?.let { l ->
            return persistence.create(codeInputToUuidInput(element, l.id))
        }
        return null
    }

    override fun updateOne(
        element: InputCode,
        id: IdCode
    ): Output? {
        languageService.getOneByCodes(id.language)?.let { l ->
            return persistence.updateOne(
                codeInputToUuidInput(element, l.id),
                codeIdToUuidId(id, l.id)
            )
        }
        return null
    }

    override fun getOne(id: IdCode): Output? {
        languageService.getOneByCodes(id.language)?.let { l ->
            return persistence.getOne(codeIdToUuidId(id, l.id))
        }
        return null
    }

    override fun getAllByLanguage(codes: String): Collection<Output> {
        languageService.getOneByCodes(codes)?.let { l ->
            return persistence.getAllByLanguage(l.id)
        }
        return listOf();
    }

    override fun getPageByLanguage(pagination: IPagination, codes: String): IPage<Output> {
        languageService.getOneByCodes(codes)?.let { l ->
            return  persistence.getAllTotalByLanguage(pagination, l.id).toPage(pagination)
        }
        return StaticPage.emptyPage(pagination);
    }

    override fun getAll(): Collection<Output> {
        TODO("Not yet implemented")
    }

    override fun getPage(pagination: IPagination): IPage<Output> {
        TODO("Not yet implemented")
    }
}