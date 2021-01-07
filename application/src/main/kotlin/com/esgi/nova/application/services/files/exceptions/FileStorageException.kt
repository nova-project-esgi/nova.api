package com.esgi.nova.application.services.files.exceptions

class FileStorageException(val msg: String) : RuntimeException() {

    companion object {
        private const val serialVersionUID = 1L
    }
}