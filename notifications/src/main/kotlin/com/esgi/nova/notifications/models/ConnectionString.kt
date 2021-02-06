package com.esgi.nova.notifications.models

import java.net.URI

public class ConnectionString {
    companion object {
        fun createUsingSharedAccessKey(endPoint: URI?, keyName: String?, accessSecret: String?): String {
            when {
                endPoint == null -> {
                    throw IllegalArgumentException("endPoint");
                }
                keyName.isNullOrBlank() -> {
                    throw IllegalArgumentException("keyName");
                }
                accessSecret.isNullOrBlank() -> {
                    throw IllegalArgumentException("accessSecret");
                }
                else -> {
                    return String.format("Endpoint=%s;SharedAccessKeyName=%s;SharedAccessKey=%s", endPoint.toString(), keyName, accessSecret);
                }
            }
        }
    }

}