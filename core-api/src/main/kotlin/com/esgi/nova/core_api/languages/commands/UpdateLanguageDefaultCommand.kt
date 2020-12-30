package com.esgi.nova.core_api.languages.commands

import com.esgi.nova.core_api.languages.LanguageIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateLanguageDefaultCommand(@TargetAggregateIdentifier val languageId: LanguageIdentifier, val isDefault: Boolean)