package com.esgi.nova

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.File


@Component
class StartupComponent() : CommandLineRunner {
    @Value("\${app.upload.dir:\${user.home}}")
    lateinit var uploadDir: String
    var logger: Logger = LoggerFactory.getLogger(StartupComponent::class.java)
    override fun run(vararg args: String) {
        val dir = File(uploadDir)
        dir.mkdir()
        logger.info("CREATE UPLOAD DIR : ${dir.absolutePath}")
        val resourcesDir = File(dir, "resources")
        logger.info("CREATE RESOURCES DIR : ${resourcesDir.absolutePath}")
        resourcesDir.mkdir()
        val eventsDir = File(dir, "events")
        logger.info("CREATE EVENTS DIR : ${eventsDir.absolutePath}")
        eventsDir.mkdir()
    }

}