package com.esgi.nova.ports.provided.dtos.choice_translation

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.ITranslation
import java.util.*

class ChoiceTranslationResumeDto(override var title: String,
                                 override var description: String,
                                 val choice: UUID,
                                 override var id: UUID,
                                 override var language: UUID) : IChoiceTranslation, IId<UUID>, ITranslation<UUID> {
}