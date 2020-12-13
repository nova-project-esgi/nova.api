package com.esgi.nova.query.choice.query_handlers

import com.esgi.nova.core_api.choices.GetOneChoiceQuery
import com.esgi.nova.core_api.choices.views.ChoiceView
import com.esgi.nova.query.choice.ChoiceRepository
import com.esgi.nova.query.extensions.findNullableById
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class GetOneChoiceHandler constructor(private val choiceRepository: ChoiceRepository) {

    @QueryHandler
    fun handle(query: GetOneChoiceQuery): ChoiceView? {
        return choiceRepository.findNullableById(query.id)?.toChoiceView()
    }
}