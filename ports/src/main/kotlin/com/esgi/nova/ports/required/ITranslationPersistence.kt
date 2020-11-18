package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.IPagination
import java.util.*

interface ITranslationPersistence<Id, InputDto, OutputDto>: ICrudPersistence<Id, InputDto, OutputDto> {
    fun getAllTotalByLanguage(pagination: IPagination, languageId: UUID): ITotalCollection<OutputDto>
    fun getAllByLanguage(languageId: UUID): Collection<OutputDto>
}