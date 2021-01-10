package com.esgi.nova.files.core.api.commands

import com.esgi.nova.files.core.api.FileIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteFileCommand(@TargetAggregateIdentifier val fileId: FileIdentifier) {
}