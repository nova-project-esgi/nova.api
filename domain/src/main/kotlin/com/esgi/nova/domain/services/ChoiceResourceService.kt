package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.ITotalIterable
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.services.IChoiceResourceService
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject
import java.awt.Choice

class ChoiceResourceService @Inject constructor(private val choiceResourcePersistence: IChoiceResourcePersistence) :
    IChoiceResourceService {
    override fun getAll(query: Query) = choiceResourcePersistence.getAll(query)
    override fun getAllTotal(query: Query): ITotalIterable<Choice> {
        TODO("Not yet implemented")
    }
}