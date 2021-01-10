package com.esgi.nova.files.core.api.commands

import com.esgi.nova.files.core.api.FileIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.nio.file.Path

data class CreateFileCommand(
    @TargetAggregateIdentifier
        val fileId: FileIdentifier,
    val path: Path
) {
}