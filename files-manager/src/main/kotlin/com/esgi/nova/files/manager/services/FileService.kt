package com.esgi.nova.files.manager.services

import com.esgi.nova.common.extensions.withoutExtension
import com.esgi.nova.files.core.api.FileIdentifier
import com.esgi.nova.files.core.api.commands.CreateFileCommand
import com.esgi.nova.files.core.api.commands.DeleteFileCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Paths
import java.util.*


@Service
abstract class FileService(
    protected val commandGateway: CommandGateway,
    protected val queryGateway: QueryGateway
) {
    abstract fun deleteObsoleteFilesInDir(dir: File)


    fun updateFiles(files: Set<File>) {
//        this.commandGateway.sendAndWait<>()
    }

    fun deleteFiles(files: Set<File>) {
        files.forEach { f -> commandGateway.sendAndWait<DeleteFileCommand>(FileIdentifier(f.name.withoutExtension())) }
    }

    fun addFiles(files: Set<File>) {
        files.forEach { f ->
            if (!validateFileName(f)) {
                f.delete()
            } else {
                commandGateway.sendAndWait<FileIdentifier>(
                    CreateFileCommand(
                        fileId = FileIdentifier(f.name.withoutExtension()),
                        path = f.toPath()
                    )
                )
            }

        }
    }

    protected fun removeInvalidateFileNames(files: Set<File>): Set<File> {
        val keepFiles = mutableSetOf<File>()
        files.forEach { f ->
            if (validateFileName(f))
                keepFiles.add(f)
            else
                f.delete()
        }
        return keepFiles
    }

    protected fun validateFileName(file: File): Boolean {
        try {
            UUID.fromString(file.name.withoutExtension())
        } catch (e: Exception) {
            return false;
        }
        return true;
    }

}

