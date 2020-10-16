package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.ChoiceResourceDto

interface IChoiceResourcePersistence {
    fun getAll(): List<ChoiceResourceDto>
}