package com.alongo.discordbot.domain.scenario

import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.event.message.MessageCreateEvent
import com.alongo.discordbot.domain.usecase.GenerateQrCodeUseCase
import java.io.ByteArrayInputStream

class GenerateQrCodeFromTextScenario(
    private val generateQrCodeUseCase: GenerateQrCodeUseCase,
) {
    suspend operator fun invoke(contentToEncode: String, event: MessageCreateEvent) {
        val qrCodeByteArray = generateQrCodeUseCase(contentToEncode, 300, 300)
        event.message.channel.createMessage("${event.message.author?.mention} has generated the attached qr code:")
        event.message.channel.createMessage {
            addFile("qrcode.png", ByteArrayInputStream(qrCodeByteArray))
        }
    }
}
