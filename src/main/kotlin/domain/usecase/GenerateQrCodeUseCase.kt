package domain.usecase

import utils.QrCodeGenerator

// GenerateQrCodeUseCase is used for generating image files containing qr encoded data
class GenerateQrCodeUseCase(private val qrCodeGenerator: QrCodeGenerator) {
    suspend operator fun invoke(data: String, path: String, h: Int, w: Int) {
        qrCodeGenerator.generateQrCode(data = data, path = path, h = h, w = w)
    }
}
