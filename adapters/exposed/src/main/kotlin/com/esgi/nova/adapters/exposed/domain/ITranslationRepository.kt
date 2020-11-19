package com.esgi.nova.adapters.exposed.domain

import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import java.util.*

interface ITranslationRepository<Id, Input,  Output>: IRepository<Id, Input, Output> {
    fun getAllTotalByLanguage(pagination: DatabasePagination, languageId: UUID): ITotalCollection<Output>
    fun getAllByLanguage(languageId: UUID): SizedIterable<Output>
}

