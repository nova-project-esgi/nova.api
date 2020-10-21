package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.services.IChoiceService
import com.esgi.nova.ports.required.IChoicePersistence
import com.google.inject.Inject

class ChoiceService @Inject constructor(private val choicePersistence: IChoicePersistence) : IChoiceService {
    override fun getAll(query: Query) = choicePersistence.getAll(query)
}