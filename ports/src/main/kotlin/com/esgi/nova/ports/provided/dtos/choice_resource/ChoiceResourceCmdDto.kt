package com.esgi.nova.ports.provided.dtos.choice_resource

import java.util.*

data class ChoiceResourceCmdDto(var choiceId: UUID, var resourceId: UUID, override var changeValue: Int) :
    IChoiceResource {
}