package com.esgi.nova.file_manager

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import java.io.IOException

import java.nio.file.StandardOpenOption

import java.nio.channels.FileChannel

import org.springframework.boot.devtools.filewatch.*
import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Path

@Component
class MyFileChangeListener : FileChangeListener {

    val logger: Logger = LoggerFactory.getLogger(MyFileChangeListener::class.java)


    override fun onChange(changeSet: Set<ChangedFiles>) {
        logger.info("JE POLL")
        for (files in changeSet) {
            for (file in files.files) {
                if (file.type == ChangedFile.Type.ADD &&  !isLocked(file.file.toPath())) {
                    println(
                        "Operation: " + file.type
                                + " On file: " + file.file.name + " is done"
                    )
                }
            }
        }
    }

    private fun isLocked(path: Path): Boolean {
        try {
            FileChannel.open(path, StandardOpenOption.WRITE).use { ch ->
                ch.tryLock().use { lock -> return lock == null }
            }
        } catch (e: IOException) {
            return true
        }
    }
}