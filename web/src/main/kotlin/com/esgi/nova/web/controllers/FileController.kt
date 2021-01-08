package com.esgi.nova.web.controllers

import com.esgi.nova.application.uses_cases.files.FileUseCases
import com.esgi.nova.core_api.games.views.GameView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.ServletContext

@RestController
@RequestMapping("files")
open class FileController constructor(
        private val fileUseCases: FileUseCases,
        private val context: ServletContext
) {
    @GetMapping("test")
    fun getOneById() {
        fileUseCases.createTestFile()
    }
}
