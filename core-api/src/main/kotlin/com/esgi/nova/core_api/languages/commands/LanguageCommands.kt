package com.esgi.nova.core_api.languages.commands

import com.esgi.nova.core_api.languages.LanguageIdentifier
import org.axonframework.commandhandling.gateway.CommandGateway

class LanguageCommands constructor(val commandGateway: CommandGateway) {

    fun createLanguage(code: String, subCode: String?): String = commandGateway.sendAndWait<LanguageIdentifier>(
            CreateLanguageCommand(
                    id = LanguageIdentifier(), code = code, subCode = subCode
            )
    ).identifier


    fun updateLanguage(id: LanguageIdentifier, code: String, subCode: String?): Unit = commandGateway.sendAndWait(
            UpdateLanguageCommand(
                    languageId = id, code = code, subCode = subCode
            )
    )

}