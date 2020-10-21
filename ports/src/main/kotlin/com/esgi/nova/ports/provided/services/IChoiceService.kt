package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.choice.ChoiceDto
import com.esgi.nova.ports.required.IGetAll

interface IChoiceService: IGetAll<ChoiceDto> {
}