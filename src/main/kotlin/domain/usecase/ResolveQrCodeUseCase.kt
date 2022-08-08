package domain.usecase

import com.google.zxing.DecodeHintType
import utils.qr.QrCodeResolver
import java.io.InputStream

class ResolveQrCodeUseCase(private val qrCodeResolver: QrCodeResolver) {

    suspend operator fun invoke(inputStream: InputStream): String {
        val hints = mapOf(DecodeHintType.CHARACTER_SET to "UTF-8")
        return qrCodeResolver.readQRCode(inputStream, hints)
    }
}
