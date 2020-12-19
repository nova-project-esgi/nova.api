package com.esgi.nova.file_manager.application.init_treatment

import org.axonframework.commandhandling.CommandHandler
import org.springframework.stereotype.Component

@Component
open class InitTreatmentCommandHandler {

    @CommandHandler
    open fun handle(cmd: InitTreatmentCommand){
        println(cmd)
    }

}