package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.language.LanguageCmdDto
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import java.util.*

interface ILanguagePersistence :
    ICrudPersistence<UUID, LanguageCmdDto, LanguageDto> {
    fun getAllTotalByCodes(pagination: IPagination, code: String, subCode: String?): ITotalCollection<LanguageDto>
    fun getAllByCodes(code: String, subCode: String?): Collection<LanguageDto>
    fun getOneByCodes(code: String, subCode: String?): LanguageDto?
    fun getDefault(): LanguageDto?
}