package utils

import java.io.InputStream
import java.nio.file.Files
import kotlin.io.path.Path

object FileUtils {
    fun getResourceAsStream(name: String): InputStream? = object {}::class.java.getResourceAsStream(name)
    fun deleteFileFromPath(string: String) {
        Files.delete(Path(string))
    }
}
