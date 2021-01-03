package com.esgi.nova

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.File


@Component
class StartupComponent() : CommandLineRunner {
    @Value("\${app.upload.dir:\${user.home}}")
    lateinit var uploadDir: String

    override fun run(vararg args: String) {
        val dir = File(uploadDir)
        dir.mkdir()
        File(dir, "resources").mkdir()
        File(dir, "events").mkdir()
    }

}