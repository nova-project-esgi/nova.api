package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.ChoiceResourceDto

interface IChoiceResourceService {
    fun getAll(): List<ChoiceResourceDto>
}