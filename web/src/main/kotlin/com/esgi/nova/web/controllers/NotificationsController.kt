package com.esgi.nova.web.controllers

import com.esgi.nova.application.pagination.PageMetadata
import com.esgi.nova.core_api.languages.views.LanguageView
import com.esgi.nova.notifications.dtos.NotificationDto
import com.esgi.nova.notifications.services.NotificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("notifications")
open class NotificationsController(private val notificationService: NotificationService) {

    @PostMapping()
    open fun sendNotification(@RequestBody notification: NotificationDto): ResponseEntity<Any> {
        notificationService.sendNotification(notification);
        return ResponseEntity.ok().build()
    }
}