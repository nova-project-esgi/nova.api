package com.esgi.nova.file_manager.configuration

import com.esgi.nova.file_manager.MyFileChangeListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value

import org.springframework.boot.devtools.filewatch.FileSystemWatcher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File
import java.time.Duration

@Configuration
open class FileWatcherConfig {

    @Value("\${app.upload.dir:\${user.home}}")
    lateinit var uploadDir: String

    @Bean
    open fun fileSystemWatcher(): FileSystemWatcher {
        val fileSystemWatcher = FileSystemWatcher(true, Duration.ofMillis(100), Duration.ofMillis(100))
        fileSystemWatcher.addSourceDirectory(File("$uploadDir/resources"))
        fileSystemWatcher.addSourceDirectory(File("$uploadDir/events"))
        fileSystemWatcher.addListener(MyFileChangeListener())
        fileSystemWatcher.start()
        return fileSystemWatcher
    }

    fun onDestroy() {
        fileSystemWatcher().stop()
    }
}