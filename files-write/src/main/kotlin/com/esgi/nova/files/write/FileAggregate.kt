package com.esgi.nova.files.write

import com.esgi.nova.files.core.api.FileIdentifier
import com.esgi.nova.files.core.api.commands.CreateFileCommand
import com.esgi.nova.files.core.api.commands.DeleteFileCommand
import com.esgi.nova.files.core.api.events.CreatedFileEvent
import com.esgi.nova.files.core.api.events.DeletedFileEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.nio.file.Path

@Aggregate
class FileAggregate() {

    @AggregateIdentifier
    private lateinit var id: FileIdentifier
    private lateinit var path: Path

    @CommandHandler
    constructor(cmd: CreateFileCommand) : this() {
        AggregateLifecycle.apply(
                CreatedFileEvent(
                        fileId = cmd.fileId,
                        path = cmd.path
                )
        )
    }

    @EventSourcingHandler
    fun on(event: CreatedFileEvent) {
        id = event.fileId
        path = event.path
    }

    @CommandHandler
    fun handle(cmd: DeleteFileCommand) {
        AggregateLifecycle.apply(DeletedFileEvent(fileId = cmd.fileId, path = path))
    }

    @EventSourcingHandler
    fun on(event: DeletedFileEvent) {
        AggregateLifecycle.markDeleted()
    }
}