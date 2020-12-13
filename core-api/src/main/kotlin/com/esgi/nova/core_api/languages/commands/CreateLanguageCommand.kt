package com.esgi.nova.core_api.languages.commands

import com.esgi.nova.core_api.languages.LanguageIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateLanguageCommand(@TargetAggregateIdentifier val id: LanguageIdentifier, val code: String, val subCode: String?)