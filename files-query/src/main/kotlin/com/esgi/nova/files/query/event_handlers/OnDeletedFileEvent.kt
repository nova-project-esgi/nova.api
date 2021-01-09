package com.esgi.nova.files.query.event_handlers

import com.esgi.nova.files.core.api.events.DeletedFileEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component
import java.nio.file.Files

@Component
open class OnDeletedFileEvent constructor(
) {

    @EventHandler
    fun on(event: DeletedFileEvent) {
        if (Files.exists(event.path)) {
            event.path.toFile().delete()
        }
    }
}

