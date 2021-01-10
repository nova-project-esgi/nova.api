package com.esgi.nova.files.core.api.events

import com.esgi.nova.files.core.api.FileIdentifier
import java.nio.file.Path

data class CreatedFileEvent(val fileId: FileIdentifier, val path: Path) {
}