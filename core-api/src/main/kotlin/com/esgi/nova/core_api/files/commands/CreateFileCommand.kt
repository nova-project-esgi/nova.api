package com.esgi.nova.core_api.files.commands

import com.esgi.nova.core_api.files.FileIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.nio.file.Path

data class CreateFileCommand(
        @TargetAggregateIdentifier
        val fileId: FileIdentifier,
        val path: Path
) {
}