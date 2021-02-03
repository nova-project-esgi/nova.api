package com.esgi.nova.files.infra

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.File


@Component
class StartupComponent(private val settings: UploadSettings) : CommandLineRunner {
    var logger: Logger = LoggerFactory.getLogger(StartupComponent::class.java)
    override fun run(vararg args: String) {
        val dir = File(settings.dir)
        dir.mkdir()
        logger.info("UPLOAD DIR : ${dir.absolutePath}")
        val resourcesDir = File(dir, settings.resources)
        logger.info("RESOURCES DIR : ${resourcesDir.absolutePath}")
        resourcesDir.mkdir()
        val eventsDir = File(dir, settings.events)
        logger.info("EVENTS DIR : ${eventsDir.absolutePath}")
        eventsDir.mkdir()
        val testDir = File(dir, "testDir")
        logger.info("TEST DIR : ${testDir.absolutePath}")
        testDir.mkdir()
        val testFile = File(testDir, "test231534.png")
        testFile.createNewFile()

    }

}