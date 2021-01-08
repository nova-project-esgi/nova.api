package com.esgi.nova.core_api.files.commands

import com.esgi.nova.core_api.files.FileIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteFileCommand(@TargetAggregateIdentifier val fileId: FileIdentifier) {
}