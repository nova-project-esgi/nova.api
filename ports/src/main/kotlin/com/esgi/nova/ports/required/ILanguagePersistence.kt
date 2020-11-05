package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.language.LanguageCmdDto
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import java.util.*

interface ILanguagePersistence : IGetAll<LanguageDto>, ICreate<LanguageCmdDto, LanguageDto>, IUpdateOne<LanguageCmdDto, UUID, LanguageDto>,
    IGetAllTotal<LanguageDto>,
    IGetOne<UUID, LanguageDto> {
    fun getAllTotalByCodes(pagination: IPagination, code: String, subCode: String?): ITotalCollection<LanguageDto>
    fun getAllByCodes(code: String, subCode: String?): List<LanguageDto>
    fun getOneByCodes(code: String, subCode: String?): LanguageDto?
    fun getDefault(): LanguageDto?
}