package com.alongo.discordbot.domain.message_handlers.qr

import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import com.alongo.discordbot.domain.usecase.ResolveQrCodeUseCase
import com.alongo.discordbot.utils.FileUtils
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.Attachment
import dev.kord.core.event.message.MessageCreateEvent

class QrDecodeMessageHandler(
    private val resolveQrCodeUseCase: ResolveQrCodeUseCase
) : BaseMessageHandler() {

    override suspend fun handle(command: String, event: MessageCreateEvent) {
        val channel = event.message.channel
        val senderUsername = event.message.author?.mention ?: "User"
        val messageAttachments = event.message.attachments

        if (messageAttachments.size != 1 || !messageAttachments.first().isImage) {
            channel.createMessage("${senderUsername}, please attach exactly one image file.")
            return
        }

        val fileToResolve: Attachment = messageAttachments.first()
        val inputStream = FileUtils.downloadFile(fileToResolve.url)

        try {
            val resolvedQrCodeText = resolveQrCodeUseCase(inputStream)
            channel.createMessage {
                this.content =
                    "${senderUsername}, the bot has found \"$resolvedQrCodeText\" encoded in your picture."
            }
        } catch (e: Exception) {
            channel.createMessage("${senderUsername}, the bot was unable to find any QR codes in the provided picture")
        }
    }
}