package com.esgi.nova.file_manager.exposition

import com.esgi.nova.file_manager.application.init_treatment.InitTreatmentCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("files")
open class FileManagerController(private val commandGateway: CommandGateway, private val queryGateway: QueryGateway) {

    @PreAuthorize("permitAll()")
    @GetMapping("test")
    open fun testController(): ResponseEntity<Any>{
        this.commandGateway.sendAndWait<UUID>(InitTreatmentCommand())
        return ok().build();
    }
}