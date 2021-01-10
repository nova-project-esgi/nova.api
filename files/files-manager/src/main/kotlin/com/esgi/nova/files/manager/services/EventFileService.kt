package com.esgi.nova.files.manager.services

import com.esgi.nova.common.extensions.withoutExtension
import com.esgi.nova.core_api.events.EventIdentifier
import com.esgi.nova.core_api.events.queries.FindActiveStoreEventsQuery
import com.esgi.nova.files.core.api.FileIdentifier
import com.esgi.nova.files.core.api.commands.DeleteFileCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.queryMany
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.io.File
import java.util.*

@Service
open class EventFileService(commandGateway: CommandGateway, queryGateway: QueryGateway) : FileService(
    commandGateway,
    queryGateway
) {

    override fun deleteObsoleteFilesInDir(dir: File) {
        dir.listFiles()?.let { files ->
            val validFiles = removeInvalidateFileNames(files.toSet())
            val eventIds = queryGateway
                .queryMany<UUID, FindActiveStoreEventsQuery>(
                    FindActiveStoreEventsQuery(
                        ids = validFiles.map { EventIdentifier(it.name.withoutExtension()) }
                    )
                ).join()
            validFiles.filter { file -> eventIds.none { eventId -> file.name.withoutExtension() == eventId.toString() } }
                .forEach { file ->
                    commandGateway.send<FileIdentifier>(DeleteFileCommand(FileIdentifier(file.name.withoutExtension())))
                }
        }

    }
}