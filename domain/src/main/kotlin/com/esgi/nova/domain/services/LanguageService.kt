package com.esgi.nova.domain.services

import com.esgi.nova.domain.domain.StaticPage
import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.domain.services.service.BaseService
import com.esgi.nova.ports.common.constants.MultiLanguage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.language.LanguageCmdDto
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import com.esgi.nova.ports.provided.services.ILanguageService
import com.esgi.nova.ports.required.ILanguagePersistence
import com.google.inject.Inject
import java.util.*

class LanguageService @Inject constructor(override val persistence: ILanguagePersistence)  : BaseService<UUID, LanguageCmdDto, LanguageDto>(persistence), ILanguageService {
    override fun getPageByCodes(pagination: IPagination, codes: String): IPage<LanguageDto> {
        val (code, subCode) = getCodeAndSubCodeFromCodes(codes)
        code?.let {
            return persistence.getAllTotalByCodes(pagination, code, subCode).toStaticPage(pagination)
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
                return persistence.getAll().toList()
            }
            return persistence.getAllByCodes(code, subCode).toList()
        }
        return listOf()
    }

    override fun createByCodes(codes: String): LanguageDto? {
        val (code, subCode) = getCodeAndSubCodeFromCodes(codes)
        code?.let {
            return persistence.create(LanguageCmdDto(code, subCode))
        }
        return null
    }

    override fun getOneByCodes(codes: String): LanguageDto? {
        val (code, subCode) = getCodeAndSubCodeFromCodes(codes)
        code?.let {
            if (code != MultiLanguage.ALL) {
                return persistence.getOneByCodes(code, subCode) ?: persistence.getOneByCodes(
                    MultiLanguage.DEFAULT_CODE,
                    null
                )
            }
        }
        return null
    }

}