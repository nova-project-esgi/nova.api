package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.ChoiceDto

interface IChoiceService {
    fun getAll(): List<ChoiceDto>
}