package com.esgi.nova.ports.provided.services.resources

import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationKey
import com.esgi.nova.ports.provided.services.ICrudService
import com.esgi.nova.ports.provided.services.ITranslationService

interface IResourceTranslationCodesService : ICrudService<ResourceTranslationKey<String>, ResourceTranslationCmdDto<String>, ResourceTranslationDto>
