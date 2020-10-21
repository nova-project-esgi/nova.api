package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.required.IGetAll
import com.esgi.nova.ports.required.IGetAllTotal
import java.awt.Choice

interface IChoiceResourceService: IGetAll<ChoiceResourceDto>, IGetAllTotal<Choice> {
}