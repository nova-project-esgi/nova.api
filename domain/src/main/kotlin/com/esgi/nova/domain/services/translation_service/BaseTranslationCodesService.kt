package com.esgi.nova.domain.services.translation_service

import com.esgi.nova.domain.domain.StaticPage
import com.esgi.nova.domain.extensions.toPage
import com.esgi.nova.domain.services.service.IGetAllService
import com.esgi.nova.domain.services.service.IGetPageService
import com.esgi.nova.ports.common.IGetAllById
import com.esgi.nova.ports.common.IGetAllByIds
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.ITranslation
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.provided.services.ITranslationService
import com.esgi.nova.ports.required.ICrudPersistence
import com.esgi.nova.ports.required.IGetAllTotalById
import java.util.*

abstract class BaseTranslationCodesService<IdCode : ITranslation<String>, IdUuid : ITranslation<UUID>, InputCode : ITranslation<String>, InputUuid : ITranslation<UUID>, Output>(
        override val languageService: ILanguageService,
        override val persistence: ICrudPersistence<IdUuid, InputUuid, Output>,
        val getAllPersistence: IGetAllById<UUID, Output>,
        val getAllTotalPersistence: IGetAllTotalById<UUID, Output>) :
        ICodeIdToUuidId<IdCode, IdUuid>,
        ICodeInputToUuidInput<InputCode, InputUuid>,
        ICreateTranslationCodeService<InputCode,InputUuid, Output>,
        IGetOneTranslationCodeService<IdCode, IdUuid, Output>,
        IUpdateTranslationCodeService<InputCode, InputUuid, IdCode, IdUuid, Output>,
        IGetAllService<Output>,
        IGetPageService<Output>,
        ITranslationService<String, Output> {

    override val idMapper by lazy { this }
    override val inputMapper by lazy { this }

    override fun getAllByLanguage(language: String): Collection<Output> {
        languageService.getOneByCodes(language)?.let { l ->
            return getAllPersistence.getAllById(l.id)
        }
        return listOf()
    }
    override fun getPageByLanguage(pagination: IPagination, language: String): IPage<Output> {
        languageService.getOneByCodes(language)?.let { l ->
            return getAllTotalPersistence.getAllTotalById(pagination, l.id).toPage(pagination)
        }
        return StaticPage.emptyPage(pagination)
    }

}