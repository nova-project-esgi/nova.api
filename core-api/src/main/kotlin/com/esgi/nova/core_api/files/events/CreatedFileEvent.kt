package com.esgi.nova.core_api.files.events

import com.esgi.nova.core_api.files.FileIdentifier
import java.nio.file.Path

data class CreatedFileEvent(val fileId: FileIdentifier, val path: Path) {
}