package com.esgi.nova.notifications.services

import com.esgi.nova.notifications.dtos.NotificationDto
import com.esgi.nova.notifications.models.AndroidNotification
import com.esgi.nova.notifications.models.AndroidNotificationMessage
import com.esgi.nova.notifications.models.NotificationSettings
import com.fasterxml.jackson.databind.ObjectMapper
import com.windowsazure.messaging.Notification
import com.windowsazure.messaging.NotificationHub
import org.springframework.stereotype.Service


@Service
open class NotificationService(notificationSettings: NotificationSettings) {

    val hub = NotificationHub(notificationSettings.connectionString, notificationSettings.nhName)

    fun sendNotification(notification: NotificationDto) {
        val objectMapper = ObjectMapper()
        val androidNotification = AndroidNotificationMessage(
                notification = AndroidNotification(notification.title, notification.body),
                data = mapOf("property1" to notification.type.name)
        )
        val notificationBody = objectMapper.writeValueAsString(androidNotification)
        val n: Notification = Notification.createFcmNotification(notificationBody)
        hub.sendNotification(n)
    }

}