package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.ChoiceDto

interface IChoicePersistence {
    fun getAll(): List<ChoiceDto>
}