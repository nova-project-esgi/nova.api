package com.esgi.nova.adapters.web.endpoints.languages

import com.esgi.nova.adapters.web.domain.pagination.PageMetadata
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.ports.provided.dtos.language.LanguageCmdDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.ILanguageService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class LanguageRoute @Inject constructor(application: Application, languageService: ILanguageService) {
    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    post("/languages") {
                        val language = call.receive<LanguageCmdDto>()
                        val createdLanguage = languageService.create(language)
                        createdLanguage?.let {
                            call.createdIn(LanguageLocation(createdLanguage.id))
                        }
                    }
                    get<LanguageLocation> {
                        val location = languageService.getOne(it.id)
                        location?.let {
                            call.respond(location)
                        }
                    }
                    get<LanguagesLocation> {
                        val languagesPage = if (it.codes != null) {
                            languageService.getPageByCodes(it, it.codes)
                        } else {
                            languageService.getPage(it)
                        }
                        val meta = PageMetadata(languagesPage, call.request.uri)
                        call.respond(meta)
                    }
                }
            }
        }
    }
}