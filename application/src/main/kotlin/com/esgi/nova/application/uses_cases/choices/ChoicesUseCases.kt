package com.esgi.nova.application.uses_cases.choices

import com.esgi.nova.core_api.choices.views.ChoiceView
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service

@Service
open class ChoicesUseCases(private val commandGateway: CommandGateway, private val queryGateway: QueryGateway) {


    open fun getAll(): List<ChoiceView> {
//        return queryGateway.query<ChoiceView?, GetOneChoiceQuery>(GetOneChoiceQuery(UUID.randomUUID())).join()
//        return queryGateway.queryMany<ChoiceView, GetAllChoicesQuery>(GetAllChoicesQuery()).join()
        return listOf()
    }
}