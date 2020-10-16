package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.services.IChoiceResourceService
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject

class ChoiceResourceService @Inject constructor(private val choiceResourcePersistence: IChoiceResourcePersistence) :
    IChoiceResourceService {
    override fun getAll() = choiceResourcePersistence.getAll()
}