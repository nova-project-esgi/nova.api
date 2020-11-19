package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination

interface ITranslationService<Id, Output>{
    fun getAllByLanguage(codes: String): Collection<Output>
    fun getPageByLanguage(pagination: IPagination, codes: String): IPage<Output>
}