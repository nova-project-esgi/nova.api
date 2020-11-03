package com.esgi.nova.ports.common.extensions

import com.esgi.nova.ports.provided.dtos.language.LanguageDto

fun LanguageDto.toAggregatedCode(): String {
    this.subCode?.let {
        return "${this.code}-${this.subCode}"
    }
    return this.code
}