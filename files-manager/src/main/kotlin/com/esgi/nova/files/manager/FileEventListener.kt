package com.esgi.nova.files.manager

import com.esgi.nova.files.manager.services.FileService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException

import java.nio.file.StandardOpenOption

import java.nio.channels.FileChannel

import org.springframework.boot.devtools.filewatch.*
import java.io.File
import java.nio.file.Path


class FileEventListener(private val fileUseCases: FileService, private var srcDir: File) : FileChangeListener {

    val logger: Logger = LoggerFactory.getLogger(FileEventListener::class.java)

    override fun onChange(changeSet: Set<ChangedFiles>) {
        val addSet = mutableSetOf<File>()
        val deleteSet = mutableSetOf<File>()
        val updateSet = mutableSetOf<File>()
        val allFilesSet = mutableSetOf<File>()
        for (files in changeSet) {
            for (file in files.files) {
                if(!isLocked(file.file.toPath())){
                    when(file.type){
                        ChangedFile.Type.ADD -> addSet.add(file.file)
                        ChangedFile.Type.DELETE -> deleteSet.add(file.file)
                        ChangedFile.Type.MODIFY -> updateSet.add(file.file)
                    }
                    allFilesSet.add(file.file)
                }
            }
        }
        fileUseCases.updateFiles(updateSet)
        fileUseCases.deleteFiles(deleteSet)
        fileUseCases.addFiles(addSet)
        fileUseCases.deleteObsoleteFilesInDir(srcDir)
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