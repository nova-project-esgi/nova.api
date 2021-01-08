package com.esgi.nova.core_api.files.events

import com.esgi.nova.core_api.files.FileIdentifier
import java.io.Serializable

data class DeletedFileEvent(val fileId: FileIdentifier) : Serializable