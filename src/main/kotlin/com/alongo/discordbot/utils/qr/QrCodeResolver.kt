package com.alongo.discordbot.utils.qr

import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.NotFoundException
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.HybridBinarizer
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import javax.imageio.ImageIO
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class QrCodeResolver {
    suspend fun readQRCode(inputStream: InputStream, hintMap: Map<DecodeHintType, String>) =
        suspendCancellableCoroutine<String> { continuation ->
            try {
                val binaryBitmap = BinaryBitmap(
                    HybridBinarizer(
                        BufferedImageLuminanceSource(
                            ImageIO.read(inputStream)
                        )
                    )
                )
                val qrCodeResult = MultiFormatReader().decode(binaryBitmap, hintMap)
                continuation.resume(qrCodeResult.text)
            } catch (e: FileNotFoundException) {
                continuation.resumeWithException(e)
            } catch (e: IOException) {
                continuation.resumeWithException(e)
            } catch (e: NotFoundException) {
                continuation.resumeWithException(e)
            }
        }
}
