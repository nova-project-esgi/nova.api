package com.esgi.nova.ports.provided.dtos.language

import com.esgi.nova.ports.provided.dtos.IId
import java.util.*

data class LanguageDto(override var id: UUID, override var code: String, override var subCode: String?) : IId<UUID>,
    ILanguage {
}