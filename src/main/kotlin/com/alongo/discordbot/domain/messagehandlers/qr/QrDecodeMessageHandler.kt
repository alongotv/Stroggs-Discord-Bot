package com.alongo.discordbot.domain.messagehandlers.qr

import com.alongo.discordbot.domain.messagehandlers.BaseMessageHandler
import com.alongo.discordbot.domain.usecase.ResolveQrCodeUseCase
import com.alongo.discordbot.utils.FileUtils
import com.google.zxing.NotFoundException
import dev.kord.core.behavior.reply
import dev.kord.core.entity.Attachment
import dev.kord.core.event.message.MessageCreateEvent

class QrDecodeMessageHandler(
    private val resolveQrCodeUseCase: ResolveQrCodeUseCase
) : BaseMessageHandler() {

    override suspend fun handle(command: String, event: MessageCreateEvent) {
        val senderUsername = event.message.author?.mention ?: "User"
        val messageAttachments = event.message.attachments

        if (messageAttachments.size != 1 || !messageAttachments.first().isImage) {
            event.message.reply {
                content = "$senderUsername, please attach exactly one image file."
            }
            return
        }

        val fileToResolve: Attachment = messageAttachments.first()
        val inputStream = FileUtils.downloadFile(fileToResolve.url)

        try {
            val resolvedQrCodeText = resolveQrCodeUseCase(inputStream)
            event.message.reply {
                content =
                    "$senderUsername, the bot has found \"$resolvedQrCodeText\" encoded in your picture."
            }
        } catch (e: NotFoundException) {
            event.message.reply {
                content = "$senderUsername, the bot was unable to find any QR codes in the provided picture"
            }
        }
    }
}
