package com.esgi.nova.web.controllers

import com.esgi.nova.application.uses_cases.choices.ChoicesUseCases
import com.esgi.nova.core_api.choices.views.ChoiceView
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("choices")
open class ChoiceController constructor(val choicesUseCases: ChoicesUseCases) {

    @PreAuthorize("permitAll()")
    @GetMapping("")
    open fun getAll(): List<ChoiceView> {
        return choicesUseCases.getAll();
    }
}