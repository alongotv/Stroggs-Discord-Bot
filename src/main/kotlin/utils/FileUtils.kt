package utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.nio.file.Files
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.io.path.Path

object FileUtils {
    fun getResourceAsStream(name: String): InputStream? = object {}::class.java.getResourceAsStream(name)
    fun deleteFileFromPath(string: String) {
        Files.delete(Path(string))
    }

    suspend fun downloadFile(urlString: String): InputStream = withContext(Dispatchers.IO) {
        return@withContext suspendCancellableCoroutine<InputStream> {
            val urlConnection = URL(urlString).openConnection()
            try {
                it.resume(urlConnection.getInputStream())
            } catch (e: IOException) {
                it.resumeWithException(e)
            }
        }
    }
}
