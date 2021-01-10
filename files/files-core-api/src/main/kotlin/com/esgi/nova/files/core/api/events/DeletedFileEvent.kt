package com.esgi.nova.files.core.api.events

import com.esgi.nova.files.core.api.FileIdentifier
import java.io.Serializable
import java.nio.file.Path

data class DeletedFileEvent(val fileId: FileIdentifier, val path: Path) : Serializable