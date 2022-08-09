package domain.message_handlers.qr

import data.MessageCreateEventTransmitter
import dev.kord.core.event.message.MessageCreateEvent
import domain.message_handlers.BaseMessageHandler
import domain.scenario.GenerateQrCodeFromTextScenario

class QrEncodeMessageHandler(
    messageCreateEventTransmitter: MessageCreateEventTransmitter,
    private val generateQrCodeFromTextScenario: GenerateQrCodeFromTextScenario,
) : BaseMessageHandler(
    messageCreateEventTransmitter
) {
    override val predicate: (MessageCreateEvent) -> Boolean
        get() = { event ->
            event.message.content.startsWith("!qrencode")
        }

    override suspend fun handle(event: MessageCreateEvent) {
        val channel = event.message.channel
        val messageContent = event.message.content
        val senderUsername = event.message.author?.mention ?: "User"

        if (messageContent.length > 500) {
            channel.createMessage("${senderUsername}, your message is too long. Remove unnecessary data.")
            return
        }

        val contentToEncode = messageContent.removePrefix("!qrencode").trim()
        if (contentToEncode.isBlank()) {
            channel.createMessage("${senderUsername}, please enter a message for further encoding.")
            return
        } else {
            generateQrCodeFromTextScenario(contentToEncode, event)
        }
    }
}
