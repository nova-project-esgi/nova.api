package com.esgi.nova.files.infra

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "app.upload")
open class UploadSettings() {
    var dir: String = ""
    var events: String = "events"
    var resources: String = "resources"
}