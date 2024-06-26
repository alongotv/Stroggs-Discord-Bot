package com.alongo.discordbot.domain.scenario

import com.alongo.discordbot.domain.usecase.GenerateQrCodeUseCase
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.event.message.MessageCreateEvent
import io.ktor.client.request.forms.ChannelProvider
import io.ktor.util.cio.toByteReadChannel
import java.io.ByteArrayInputStream

class GenerateQrCodeFromTextScenario(
    private val generateQrCodeUseCase: GenerateQrCodeUseCase,
) {
    suspend operator fun invoke(contentToEncode: String, event: MessageCreateEvent) {
        val qrCodeByteArray =
            generateQrCodeUseCase(contentToEncode, QR_CODE_SIDE_SIZE, QR_CODE_SIDE_SIZE)
        event.message.channel.createMessage("${event.message.author?.mention} has generated the attached qr code:")
        event.message.channel.createMessage {
            addFile(
                "qrcode.png",
                ChannelProvider { ByteArrayInputStream(qrCodeByteArray).toByteReadChannel() }
            )
        }
    }
}

private const val QR_CODE_SIDE_SIZE = 300
