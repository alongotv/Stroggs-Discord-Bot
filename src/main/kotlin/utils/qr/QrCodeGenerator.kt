package utils.qr

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.client.j2se.MatrixToImageWriter
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.ByteArrayOutputStream
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class QrCodeGenerator {

    /** Generates PNG file containing a QR code from [data] */
    suspend fun generateQrCode(
        data: String,
        charset: String = "UTF-8",
        h: Int,
        w: Int,
        hints: Map<EncodeHintType, String>
    ): ByteArray =
        suspendCancellableCoroutine { continuation ->
            try {
                // Encode data to QR code image 2D bit matrix
                val matrix = MultiFormatWriter().encode(
                    String(data.toByteArray(charset(charset))), BarcodeFormat.QR_CODE, w, h, hints
                )
                val baos = ByteArrayOutputStream()
                MatrixToImageWriter.writeToStream(matrix, "png", baos)
                continuation.resume(baos.toByteArray())
            } catch (e: WriterException) {
                continuation.resumeWithException(e)
            } catch (e: IOException) {
                continuation.resumeWithException(e)
            }
        }
}
