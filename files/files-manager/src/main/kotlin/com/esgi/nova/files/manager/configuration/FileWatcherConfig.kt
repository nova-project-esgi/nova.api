package com.esgi.nova.files.manager.configuration

import com.esgi.nova.files.infra.UploadSettings
import com.esgi.nova.files.manager.FileEventListener
import com.esgi.nova.files.manager.services.EventFileService
import com.esgi.nova.files.manager.services.ResourceFileService
import org.springframework.boot.devtools.filewatch.FileSystemWatcher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File
import java.time.Duration

@Configuration
open class FileWatcherConfig(
    private val resourceFileService: ResourceFileService,
    private val eventFileService: EventFileService,
    private val uploadSettings: UploadSettings
) {

    @Bean
    open fun resourcesFileSystemWatcher(): FileSystemWatcher {
        val fileSystemWatcher = FileSystemWatcher(true, Duration.ofMillis(1000), Duration.ofMillis(100))
        val srcDir = File("${uploadSettings.dir}/${uploadSettings.resources}");
        fileSystemWatcher.addSourceDirectory(srcDir)
        fileSystemWatcher.addListener(FileEventListener(resourceFileService, srcDir))
        fileSystemWatcher.start()
        return fileSystemWatcher
    }

    @Bean
    open fun eventsFileSystemWatcher(): FileSystemWatcher {
        val fileSystemWatcher = FileSystemWatcher(true, Duration.ofMillis(1000), Duration.ofMillis(100))
        val srcDir = File("${uploadSettings.dir}/${uploadSettings.events}");
        fileSystemWatcher.addSourceDirectory(srcDir)
        fileSystemWatcher.addListener(FileEventListener(eventFileService, srcDir))
        fileSystemWatcher.start()
        return fileSystemWatcher
    }

    fun onDestroy() {
        resourcesFileSystemWatcher().stop()
        eventsFileSystemWatcher().stop()
    }
}