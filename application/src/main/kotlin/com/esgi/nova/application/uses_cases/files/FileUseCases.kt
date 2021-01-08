package com.esgi.nova.application.uses_cases.files

import com.esgi.nova.core_api.files.FileIdentifier
import com.esgi.nova.core_api.files.commands.CreateFileCommand
import com.esgi.nova.core_api.files.commands.DeleteFileCommand
import com.esgi.nova.core_api.files.events.DeletedFileEvent
import com.esgi.nova.core_api.resources.queries.FindActiveStoredResourcesQuery
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.queryMany
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.io.File
import java.net.URI
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

@Service
open class FileUseCases(
        private val commandGateway: CommandGateway,
        private val queryGateway: QueryGateway
) {
    fun deleteObsoleteResourceFiles() {
        val resourceIds = queryGateway
                .queryMany<UUID, FindActiveStoredResourcesQuery>(FindActiveStoredResourcesQuery(ids = listOf())).join()

    }

    fun createTestFile() {
        commandGateway.sendAndWait<FileIdentifier>(CreateFileCommand(FileIdentifier("6c8e3319-23b2-4e59-b9f5-47cf2c2de566"), Paths.get("")))
        commandGateway.sendAndWait<FileIdentifier>(DeleteFileCommand(FileIdentifier("6c8e3319-23b2-4e59-b9f5-47cf2c2de566")))
    }
}