package com.esgi.nova.application.services.files

import com.esgi.nova.files.infra.UploadSettings
import com.esgi.nova.application.services.files.exceptions.FileStorageException
import com.esgi.nova.common.extensions.withoutExtension
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils.cleanPath
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption


@Service
class FileService(private val uploadSettings: UploadSettings) {


    fun getFileByBaseName(baseName: String, subDir: String): File? {
        val dir = File(Paths.get(uploadSettings.dir + File.separator + subDir).toUri())
        return dir.listFiles()?.firstOrNull { file ->
            baseName == file.name.withoutExtension()
        }
    }

    fun fileExists(baseName: String, subDir: String): Boolean {
        return getFileByBaseName(baseName, subDir) != null
    }

    fun setFile(file: MultipartFile, subDir: String? = null, newFileName: String? = null) {
        val fileName = newFileName ?: file.originalFilename
        try {
            fileName?.let {
                getFileByBaseName(subDir ?: uploadSettings.dir, fileName)?.delete()
                uploadFile(file, subDir, newFileName);
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw FileStorageException(
                "Could not store file " + fileName
                        + ". Please try again!"
            )
        }
    }

    fun uploadFile(file: MultipartFile, subDir: String? = null, newFileName: String? = null) {
        val fileName = newFileName ?: file.originalFilename
        try {
            fileName?.let {
                val copyLocation: Path = if (subDir != null) {
                    Paths
                        .get(uploadSettings.dir + File.separator + subDir + File.separator + cleanPath(fileName))
                } else {
                    Paths
                        .get(uploadSettings.dir + File.separator + cleanPath(fileName))
                }
                Files.copy(file.inputStream, copyLocation, StandardCopyOption.REPLACE_EXISTING)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw FileStorageException(
                "Could not store file " + fileName
                        + ". Please try again!"
            )
        }
    }

    fun loadFileAsResource(subDir: String, baseFileName: String): Resource {
        this.getFileByBaseName(baseFileName, subDir)?.let { file ->
            return UrlResource(file.toPath().toUri())
        }
        throw FileNotFoundException("File not found $baseFileName")
    }
}