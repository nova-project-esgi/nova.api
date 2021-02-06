package com.esgi.nova.notifications.dtos

data class NotificationDto(val title: String , val body: String? = null, val type: NotificationType)