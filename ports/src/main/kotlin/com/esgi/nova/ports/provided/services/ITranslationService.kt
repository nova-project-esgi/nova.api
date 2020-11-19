package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination

interface ITranslationService<Language, Output>{
    fun getAllByLanguage(language: Language): Collection<Output>
    fun getPageByLanguage(pagination: IPagination, language: Language): IPage<Output>
}