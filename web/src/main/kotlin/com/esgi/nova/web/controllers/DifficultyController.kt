package com.esgi.nova.web.controllers

import com.esgi.nova.application.pagination.PageMetadata
import com.esgi.nova.application.pagination.PaginationDefault
import com.esgi.nova.application.uses_cases.difficulties.DifficultiesUseCases
import com.esgi.nova.application.uses_cases.difficulties.models.DetailedDifficultyForEdition
import com.esgi.nova.core_api.difficulty.views.DetailedDifficultyView
import com.esgi.nova.core_api.difficulty.views.DetailedDifficultyWithAvailableActionsView
import com.esgi.nova.core_api.difficulty.views.DifficultyTranslationNameView
import com.esgi.nova.core_api.difficulty.views.TranslatedDifficultyView
import com.esgi.nova.web.content_negociation.CustomMediaType
import com.esgi.nova.web.extensions.toPageMetadata
import com.esgi.nova.web.io.output.Message
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import java.util.*
import javax.servlet.ServletContext

@RestController
@RequestMapping("difficulties")
open class DifficultyController constructor(
    private val difficultiesUsesCases: DifficultiesUseCases,
    private val context: ServletContext
) {
    @GetMapping("{id}", produces = [CustomMediaType.Application.DetailedDifficultyWithAvailableActions])
    fun getOneById(@PathVariable id: UUID): ResponseEntity<DetailedDifficultyWithAvailableActionsView> {
        return ResponseEntity.ok(difficultiesUsesCases.findOneDetailedDifficultyWithActionsById(id))
    }

    @DeleteMapping("{id}")
    fun deleteOneById(@PathVariable id: UUID): ResponseEntity<Any> {
        difficultiesUsesCases.deleteOneById(id)
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = [CustomMediaType.Application.DetailedDifficulty])
    open fun getAllDetailedPaginated(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int
    ): PageMetadata<DetailedDifficultyView> {
        return difficultiesUsesCases.getAllPaginated(page, size).toPageMetadata()
    }

    @PostMapping(consumes = [CustomMediaType.Application.DetailedDifficulty])
    fun createResourceWithTranslations(
        @RequestBody difficulty: DetailedDifficultyForEdition
    ): ResponseEntity<Message> {
        val id = difficultiesUsesCases.createDetailedDifficulty(difficulty)
        return ResponseEntity
            .created(
                MvcUriComponentsBuilder.fromMethodName(DifficultyController::class.java, "getOneById", id).build()
                    .toUri()
            )
            .build()
    }

    @PutMapping("{id}", consumes = [CustomMediaType.Application.DetailedDifficulty])
    fun updateResourceWithTranslations(
        @PathVariable id: UUID,
        @RequestBody difficulty: DetailedDifficultyForEdition
    ): ResponseEntity<Any> {
        difficultiesUsesCases.updateDetailedDifficulty(id, difficulty)
        return ResponseEntity
            .noContent()
            .build()
    }


    @GetMapping(produces = [CustomMediaType.Application.DetailedDifficultyWithAvailableActions], params = ["language"])
    fun getDetailedDifficultiesWitActions(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "name", required = false) name: String,
        @RequestParam(value = "language") language: String
    ): ResponseEntity<PageMetadata<DetailedDifficultyWithAvailableActionsView>> {
        return ResponseEntity.ok(
            difficultiesUsesCases.getPaginatedDetailedDifficulty(
                page,
                size,
                name,
                language
            ).toPageMetadata()
        )
    }

    @GetMapping(produces = [CustomMediaType.Application.DifficultyName])
    open fun getPaginatedDifficultiesName(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "name") name: String,
        @RequestParam(value = "language") language: String
    ): PageMetadata<DifficultyTranslationNameView> {
        return difficultiesUsesCases.getPaginatedDifficultyNamesByNameLanguageCodeAndSubCode(
            page,
            size,
            name,
            language
        ).toPageMetadata()
    }

    @GetMapping(produces = [CustomMediaType.Application.TranslatedDifficulty])
    fun getAllStandardTranslatedDifficulties(
        @RequestParam(
            value = "language",
            required = true
        ) language: String
    ): ResponseEntity<List<TranslatedDifficultyView>> {
        return ResponseEntity.ok(
            difficultiesUsesCases.loadAllStandardDifficultiesByLanguage(
                language
            )
        )
    }


}