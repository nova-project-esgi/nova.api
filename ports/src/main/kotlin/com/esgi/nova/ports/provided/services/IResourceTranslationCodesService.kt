package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey

interface IResourceTranslationCodesService : IGetOne<ResourceTranslationKey<String>, ResourceTranslationDto>,
    ICreate<ResourceTranslationCmdDto<String>, ResourceTranslationDto>,
    IUpdateOne<ResourceTranslationCmdDto<String>, ResourceTranslationKey<String>, ResourceTranslationDto> {
}