package com.esgi.nova.files.manager.services

import com.esgi.nova.common.extensions.withoutExtension
import com.esgi.nova.files.core.api.FileIdentifier
import com.esgi.nova.files.core.api.commands.DeleteFileCommand
import com.esgi.nova.core_api.resources.ResourceIdentifier
import com.esgi.nova.core_api.resources.queries.FindActiveStoredResourcesQuery
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.queryMany
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.io.File
import java.util.*

@Service
open class ResourceFileService(commandGateway: CommandGateway, queryGateway: QueryGateway) : FileService(
    commandGateway,
    queryGateway
) {

    override fun deleteObsoleteFilesInDir(dir: File) {
        dir.listFiles()?.let { files ->
            val validFiles = removeInvalidateFileNames(files.toSet())
            val resourceIds = queryGateway
                .queryMany<UUID, FindActiveStoredResourcesQuery>(
                    FindActiveStoredResourcesQuery(
                        ids = validFiles.map { ResourceIdentifier(it.name.withoutExtension()) }
                    )
                ).join()
            validFiles.filter { file -> resourceIds.none { resourceId -> file.name.withoutExtension() == resourceId.toString() } }
                .forEach { file ->
                    commandGateway.send<FileIdentifier>(DeleteFileCommand(FileIdentifier(file.name.withoutExtension())))
                }
        }

    }
}

