package utils

import java.io.InputStream

object FileUtils {
    fun getResourceAsStream(name: String): InputStream? = object {}::class.java.getResourceAsStream(name)
}
