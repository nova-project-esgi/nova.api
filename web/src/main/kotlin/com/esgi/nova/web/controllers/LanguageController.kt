package com.esgi.nova.web.controllers

import com.esgi.nova.application.uses_cases.languages.models.LanguageForCreation
import com.esgi.nova.application.uses_cases.languages.models.LanguageForUpdate
import com.esgi.nova.application.uses_cases.languages.LanguagesUseCases
import com.esgi.nova.core_api.languages.queries.views.LanguageView
import com.esgi.nova.core_api.languages.queries.views.LanguageViewWithAvailableActions
import com.esgi.nova.web.content_negociation.CustomMediaType
import com.esgi.nova.web.extensions.toPageMetadata
import com.esgi.nova.web.io.output.Message
import com.esgi.nova.application.pagination.PageMetadata
import com.esgi.nova.application.pagination.PaginationDefault
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("languages")
open class LanguageController(private val languagesUseCases: LanguagesUseCases) {

    @Secured("ROLE_ADMIN")
    @PostMapping
    open fun create(@RequestBody language: LanguageForCreation): ResponseEntity<Any> {
        val id = languagesUseCases.create(language)
        return ResponseEntity
            .created(
                MvcUriComponentsBuilder.fromMethodName(LanguageController::class.java, "getOneById", id).build().toUri()
            )
            .build()
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("{id}")
    open fun update(@RequestBody language: LanguageForUpdate, @PathVariable id: UUID): ResponseEntity<Any>{
        languagesUseCases.update(language, id)
        return ResponseEntity.noContent().build<Any>()
    }

    @GetMapping()
    open fun getAllPaginated(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int
    ): PageMetadata<LanguageView> {
        return languagesUseCases.getAllPaginated(page, size).toPageMetadata()
    }


    @GetMapping("{id}")
    open fun getOneById(@PathVariable id: UUID): LanguageView? {
        return languagesUseCases.getOneById(id)
    }

    @GetMapping("{id}/canDelete")
    open fun canDeleteById(@PathVariable id: UUID){
        languagesUseCases.canDelete(id)
    }


    @DeleteMapping("{id}")
    open fun delete(@PathVariable id: UUID): ResponseEntity<Message> {
        languagesUseCases.deleteOneById(id)
        return ResponseEntity.noContent().build()
    }


    @GetMapping(params = ["language"])
    open fun getPaginatedLanguagesByConcatenatedCode(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "language") concatenatedCode: String
    ): PageMetadata<LanguageView> {
        languagesUseCases.getPaginatedLanguagesByConcatenatedCode(
            page,
            size,
            concatenatedCode
        )
            .let { pageBase -> return PageMetadata(pageBase) }
    }

    @GetMapping(params = ["code"], produces = [CustomMediaType.Application.LanguageWithAvailableActions])
    open fun getPaginatedLanguagesWithAvailableActionsByCodeAndSubCode(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "code") code: String,
        @RequestParam(value = "subCode", required = false) subCode: String?
    ): PageMetadata<LanguageViewWithAvailableActions> {
        languagesUseCases.getPaginatedLanguagesWithActionsByCodeAndSubCode(
            page,
            size,
            code = code,
            subCode = subCode
        )
            .let { pageBase -> return PageMetadata(pageBase) }
    }

    @GetMapping(params = [ "code"])
    open fun getPaginatedLanguagesByCodeAndSubCode(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "code") code: String,
        @RequestParam(value = "subCode", required = false) subCode: String?
    ): PageMetadata<LanguageView> {
        return languagesUseCases.getPaginatedLanguagesByCodeAndSubCode(
            page,
            size,
            code,
            subCode
        ).toPageMetadata()
    }
}
