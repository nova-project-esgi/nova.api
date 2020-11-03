package com.esgi.nova.ports.provided.dtos.language

data class LanguageCmdDto(override var code: String, override var subCode: String?) : ILanguage {
}