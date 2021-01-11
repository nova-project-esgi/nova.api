package com.esgi.nova.web.controllers

import com.esgi.nova.application.pagination.PageMetadata
import com.esgi.nova.application.pagination.PaginationDefault
import com.esgi.nova.application.services.resources.ResourcesService
import com.esgi.nova.application.services.resources.models.DetailedResourceForEdition
import com.esgi.nova.application.services.resources.models.TranslatedResourceWithIconDto
import com.esgi.nova.core_api.resources.views.ResourceTranslationNameView
import com.esgi.nova.core_api.resources.views.ResourceView
import com.esgi.nova.core_api.resources.views.ResourceWithAvailableActionsView
import com.esgi.nova.web.content_negociation.CustomMediaType
import com.esgi.nova.web.extensions.toPageMetadata
import com.esgi.nova.web.io.output.Message
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import java.util.*
import javax.servlet.ServletContext


@RestController
@RequestMapping("resources")
open class ResourceController(private val resourcesService: ResourcesService, private val context: ServletContext) {


    @GetMapping("{id}")
    fun getOneById(@PathVariable id: UUID): ResponseEntity<ResourceView> {
        return ResponseEntity.ok(resourcesService.getOneResourceById(id))
    }

    @DeleteMapping("{id}")
    fun deleteOneById(@PathVariable id: UUID): ResponseEntity<Any> {
        resourcesService.deleteOneResourceById(id)
        return ResponseEntity.noContent().build();
    }


    @PostMapping(consumes = [CustomMediaType.Application.ResourceWithTranslations])
    fun createResourceWithTranslations(
        @RequestBody resource: DetailedResourceForEdition
    ): ResponseEntity<Message> {
        val id = this.resourcesService.createResourceWithTranslations(resource)
        return ResponseEntity
            .created(
                MvcUriComponentsBuilder.fromMethodName(ResourceController::class.java, "getOneById", id).build().toUri()
            )
            .build()
    }

    @PutMapping("{id}", consumes = [CustomMediaType.Application.ResourceWithTranslations])
    fun updateResourceWithTranslations(
        @PathVariable id: UUID,
        @RequestBody resource: DetailedResourceForEdition
    ): ResponseEntity<Any> {
        this.resourcesService.updateResourceWithTranslations(id, resource)
        return ResponseEntity
            .noContent()
            .build()
    }

    @GetMapping(produces = [CustomMediaType.Application.ResourceWithTranslations], params = ["language"])
    fun getResourcesWithTranslationsByLanguageAndName(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "name") name: String,
        @RequestParam(value = "language") language: String
    ): ResponseEntity<PageMetadata<ResourceWithAvailableActionsView>> {
        return ResponseEntity.ok(
            resourcesService.getPaginatedResourcesWithTranslationsByNameAndConcatenatedCodes(
                page,
                size,
                name,
                language
            ).toPageMetadata()
        )
    }

    @GetMapping(produces = [CustomMediaType.Application.ResourceWithTranslations])
    fun getResourcesWithTranslations(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int
    ): ResponseEntity<PageMetadata<ResourceWithAvailableActionsView>> {
        return ResponseEntity.ok(
            resourcesService.getPaginatedResourcesWithTranslations(
                page,
                size
            ).toPageMetadata()
        )
    }


    @PostMapping("{id}/icon")
    fun setIcon(@RequestParam("file") file: MultipartFile, @PathVariable id: UUID): ResponseEntity<Any> {
        this.resourcesService.setResourceIcon(file, id)
        return ResponseEntity
            .created(
                MvcUriComponentsBuilder.fromMethodName(ResourceController::class.java, "getIcon", id).build().toUri()
            )
            .build()
    }


    @GetMapping("{id}/icon")
    fun getIcon(@PathVariable id: UUID): ResponseEntity<Resource> {

        val icon = resourcesService.getResourceIcon(id)
        val contentType = context.getMimeType(icon.file.absolutePath)
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + icon.filename.toString() + "\""
            )
            .body<Resource>(icon)
    }

    @GetMapping(produces = [CustomMediaType.Application.ResourceName])
    open fun getPaginatedResourceNameByConcatenatedLanguage(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "name") name: String,
        @RequestParam(value = "language") language: String
    ): PageMetadata<ResourceTranslationNameView> {
        return resourcesService.getPaginatedResourcesByNameLanguageCodeAndSubCode(
            page,
            size,
            name,
            language
        ).toPageMetadata()
    }

    @GetMapping("load",produces = [CustomMediaType.Application.TranslatedResource])
    fun loadAllStandardTranslatedResources(
        @RequestParam(
            value = "language",
            required = true
        ) language: String
    ): ResponseEntity<List<TranslatedResourceWithIconDto>> {
        return ResponseEntity.ok(
            resourcesService.loadAllStandardResourcesByLanguage(
                language,
                MvcUriComponentsBuilder.fromController(ResourceController::class.java).toUriString()
            )
        )
    }


}