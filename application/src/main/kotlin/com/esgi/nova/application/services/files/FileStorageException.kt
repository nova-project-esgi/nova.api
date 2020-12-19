package com.esgi.nova.application.services.files

class FileStorageException(val msg: String) : RuntimeException() {

    companion object {
        private const val serialVersionUID = 1L
    }
}