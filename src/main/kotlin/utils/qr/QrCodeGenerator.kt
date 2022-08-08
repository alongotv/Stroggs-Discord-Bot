package utils.qr

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.client.j2se.MatrixToImageWriter
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.io.path.Path


class QrCodeGenerator {

    /** Generates PNG file containing a QR code from [data] and saves it into [path] */
    suspend fun generateQrCode(data: String, path: String, charset: String = "UTF-8", h: Int, w: Int): String =
        suspendCancellableCoroutine { continuation ->
            try {
                // Required to support cyrillic alphabet
                val hints = mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")
                // Encode data to QR code image 2D bit matrix
                val matrix = MultiFormatWriter().encode(
                    String(data.toByteArray(charset(charset))), BarcodeFormat.QR_CODE, w, h, hints
                )
                // MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.
                // Create an image file in the provided path using the matrix
                MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), Path(path))
                continuation.resume(path)
            } catch (e: WriterException) {
                continuation.resumeWithException(e)
            } catch (e: IOException) {
                continuation.resumeWithException(e)
            }
        }
}