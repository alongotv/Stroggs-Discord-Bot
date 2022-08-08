package domain.scenario

import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.event.message.MessageCreateEvent
import domain.usecase.GenerateQrCodeUseCase
import domain.usecase.RemoveLocalFileUseCase
import kotlin.io.path.Path

class GenerateQrCodeFromTextScenario(
    private val generateQrCodeUseCase: GenerateQrCodeUseCase,
    private val removeLocalQrCodeUseCase: RemoveLocalFileUseCase
) {
    suspend operator fun invoke(contentToEncode: String, event: MessageCreateEvent) {
        val qrCodePath = "qrcode-${System.nanoTime()}.png"

        generateQrCodeUseCase(contentToEncode, qrCodePath, 300, 300)
        event.message.channel.createMessage("@${event.message.author?.username} has generated the following qr code:")
        event.message.channel.createMessage {
            addFile(Path(qrCodePath))
        }
        removeLocalQrCodeUseCase(qrCodePath)
    }
}
