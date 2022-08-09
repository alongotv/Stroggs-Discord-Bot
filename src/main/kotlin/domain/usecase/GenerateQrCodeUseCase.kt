package domain.usecase

import com.google.zxing.EncodeHintType
import utils.qr.QrCodeGenerator

// GenerateQrCodeUseCase is used for generating image files containing QR encoded data
class GenerateQrCodeUseCase(private val qrCodeGenerator: QrCodeGenerator) {
    suspend operator fun invoke(data: String, h: Int, w: Int): ByteArray {
        // Required to support cyrillic alphabet
        val hints = mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")
        return qrCodeGenerator.generateQrCode(data = data, h = h, w = w, hints = hints)
    }
}
