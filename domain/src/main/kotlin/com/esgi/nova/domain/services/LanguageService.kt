package com.esgi.nova.domain.services

import com.esgi.nova.domain.domain.StaticPage
import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.common.constants.MultiLanguage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.language.LanguageCmdDto
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.required.ILanguagePersistence
import com.google.inject.Inject
import java.util.*

class LanguageService @Inject constructor(private val languagePersistence: ILanguagePersistence) : ILanguageService {
    override fun getPageByCodes(pagination: IPagination, codes: String): IPage<LanguageDto> {
        val (code, subCode) = getCodeAndSubCodeFromCodes(codes)
        code?.let {
            return languagePersistence.getAllTotalByCodes(pagination, code, subCode).toStaticPage(pagination)
        }
        return StaticPage.emptyPage(pagination)
    }

    private fun getCodeAndSubCodeFromCodes(codes: String): Pair<String?, String?> {
        val codesList = codes.split("-")
        val code = codesList.elementAtOrNull(0)
        val subCode = codesList.elementAtOrNull(1)
        return Pair(code, subCode)
    }

    override fun getAllByCodes(codes: String): List<LanguageDto> {
        val (code, subCode) = getCodeAndSubCodeFromCodes(codes)
        code?.let {
            if (code == MultiLanguage.ALL) {
                return languagePersistence.getAll().toList()
            }
            return languagePersistence.getAllByCodes(code, subCode)
        }
        return listOf()
    }

    override fun createByCodes(codes: String): LanguageDto? {
        val (code, subCode) = getCodeAndSubCodeFromCodes(codes)
        code?.let {
            return languagePersistence.create(LanguageCmdDto(code, subCode))
        }
        return null
    }

    override fun getOneByCodes(codes: String): LanguageDto? {
        val (code, subCode) = getCodeAndSubCodeFromCodes(codes)
        code?.let {
            if (code != MultiLanguage.ALL) {
                return languagePersistence.getOneByCodes(code, subCode) ?: languagePersistence.getOneByCodes(
                    MultiLanguage.DEFAULT_CODE,
                    null
                )
            }
        }
        return null
    }

    override fun getAll(): Collection<LanguageDto> = languagePersistence.getAll()
    override fun create(element: LanguageCmdDto): LanguageDto? = languagePersistence.create(element)
    override fun getOne(id: UUID): LanguageDto? = languagePersistence.getOne(id)
    override fun getPage(pagination: IPagination): IPage<LanguageDto> =
        languagePersistence.getAllTotal(pagination).toStaticPage(pagination)
}