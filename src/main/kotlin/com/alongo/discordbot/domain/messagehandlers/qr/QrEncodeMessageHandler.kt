package com.alongo.discordbot.domain.messagehandlers.qr

import com.alongo.discordbot.domain.messagehandlers.BaseMessageHandler
import com.alongo.discordbot.domain.scenario.GenerateQrCodeFromTextScenario
import dev.kord.core.event.message.MessageCreateEvent

class QrEncodeMessageHandler(
    private val generateQrCodeFromTextScenario: GenerateQrCodeFromTextScenario,
) : BaseMessageHandler() {

    override suspend fun handle(command: String, event: MessageCreateEvent) {
        val channel = event.message.channel
        val messageContent = event.message.content
        val senderUsername = event.message.author?.mention ?: "User"

        if (messageContent.length > MAX_MESSAGE_LENGTH_SYMBOLS) {
            channel.createMessage(
                "$senderUsername, your message is too long. Remove unnecessary data."
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

private const val MAX_MESSAGE_LENGTH_SYMBOLS = 500