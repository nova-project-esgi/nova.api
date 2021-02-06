package com.esgi.nova.notifications.models

import com.esgi.nova.notifications.models.ConnectionString
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.net.URI

@Configuration
@ConfigurationProperties(prefix = "app.notifications")
open class NotificationSettings() {
    var nhName: String? = null
    var nhEndpoint: String? = null
    var nhSharedAccessKeyName: String? = null
    var nhSharedAccessKey: String? = null

    val connectionString: String
        get() = ConnectionString.createUsingSharedAccessKey(
                endPoint = URI.create(nhEndpoint),
                keyName = nhSharedAccessKeyName,
                accessSecret = nhSharedAccessKey)

}