package domain.message_handlers

import data.MessageCreateEventTransmitter
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.event.message.MessageCreateEvent
import domain.BaseMessageHandler
import domain.usecase.GenerateQrCodeUseCase
import domain.usecase.RemoveLocalFileUseCase
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.io.path.Path

class QrMessageHandler(
    messageCreateEventTransmitter: MessageCreateEventTransmitter,
    private val generateQrCodeUseCase: GenerateQrCodeUseCase,
    private val removeLocalQrCodeUseCase: RemoveLocalFileUseCase
) : BaseMessageHandler(
    messageCreateEventTransmitter
) {
    override val predicate: (MessageCreateEvent) -> Boolean
        get() = { event ->
            event.message.content.startsWith("!qrencode")
        }

    override suspend fun setup() {
        scope.launch {
            messages.collect { event ->
                val messageContent = event.message.content

                if (messageContent.length > 500) {
                    event.message.channel.createMessage("The encoded message is too long. Remove unnecessary data.")
                    return@collect
                }

                if (messageContent.contains("qrencode")) {
                    withContext(NonCancellable) {
                        val contentToEncode = messageContent.removePrefix("!qrencode ")
                        val qrCodePath = "qrcode-${System.nanoTime()}.png"

                        generateQrCodeUseCase(contentToEncode, qrCodePath, 300, 300)
                        event.message.channel.createMessage("${event.message.author?.username} has generated the following qr code:")
                        event.message.channel.createMessage {
                            addFile(Path(qrCodePath))
                        }
                        removeLocalQrCodeUseCase(qrCodePath)
                    }
                }
            }
        }
    }
}
