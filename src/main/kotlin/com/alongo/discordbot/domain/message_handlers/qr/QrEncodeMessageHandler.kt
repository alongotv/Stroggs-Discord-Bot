package com.alongo.discordbot.domain.message_handlers.qr

import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import com.alongo.discordbot.domain.scenario.GenerateQrCodeFromTextScenario
import dev.kord.core.event.message.MessageCreateEvent

class QrEncodeMessageHandler(
    private val generateQrCodeFromTextScenario: GenerateQrCodeFromTextScenario,
) : BaseMessageHandler() {

    override suspend fun handle(command: String, event: MessageCreateEvent) {
        val channel = event.message.channel
        val messageContent = event.message.content
        val senderUsername = event.message.author?.mention ?: "User"

        if (messageContent.length > 500) {
            channel.createMessage(
                "$senderUsername, your message is too long. Remove unnecessary com.alongo.discordbot.data."
            )
            return
        }

        val contentToEncode = messageContent.removePrefix("!qrencode").trim()
        if (contentToEncode.isBlank()) {
            channel.createMessage("$senderUsername, please enter a message for further encoding.")
            return
        } else {
            generateQrCodeFromTextScenario(contentToEncode, event)
        }
    }
}
