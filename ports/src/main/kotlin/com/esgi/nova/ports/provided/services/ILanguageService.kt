package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.language.LanguageCmdDto
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import java.util.*

interface ILanguageService : IGetAll<LanguageDto>, ICreate<LanguageCmdDto, LanguageDto>, IGetOne<UUID, LanguageDto>,
    IGetPage<LanguageDto> {
    fun getPageByCodes(pagination: IPagination, codes: String): IPage<LanguageDto>
    fun getAllByCodes(codes: String): List<LanguageDto>
    fun createByCodes(codes: String): LanguageDto?
    fun getOneByCodes(codes: String): LanguageDto?

}