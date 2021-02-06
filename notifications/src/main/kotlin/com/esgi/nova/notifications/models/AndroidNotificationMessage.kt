package com.esgi.nova.notifications.models

data class AndroidNotificationMessage(val notification: AndroidNotification, val data: Map<String, String>)