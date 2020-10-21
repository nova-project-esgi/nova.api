package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto

interface IChoiceResourcePersistence : IGetAll<ChoiceResourceDto>, ICreate<ChoiceResourceDto, ChoiceResourceDto>,
    IGetAllTotal<ChoiceResourceDto> {
}