package com.esgi.nova.web.controllers

import com.esgi.nova.application.uses_cases.languages.LanguageForEdition
import com.esgi.nova.application.uses_cases.languages.LanguagesUseCases
import com.esgi.nova.core_api.languages.queries.views.LanguageView
import com.esgi.nova.web.extensions.toPageMetadata
import com.esgi.nova.web.io.output.Message
import com.esgi.nova.web.pagination.PageMetadata
import com.esgi.nova.web.pagination.PaginationDefault
import org.springframework.http.HttpStatus
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
    open fun create(@RequestBody language: LanguageForEdition): ResponseEntity<Message> {
        val id = languagesUseCases.create(language)
        return ResponseEntity
            .created(
                MvcUriComponentsBuilder.fromMethodName(LanguageController::class.java, "getOneById", id).build().toUri()
            )
            .build()
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("{id}")
    open fun update(@RequestBody language: LanguageForEdition, @PathVariable id: UUID): ResponseEntity<Any>{
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
