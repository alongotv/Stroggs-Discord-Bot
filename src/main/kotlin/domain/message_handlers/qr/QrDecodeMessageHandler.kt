package domain.message_handlers.qr

import data.MessageCreateEventTransmitter
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.Attachment
import dev.kord.core.event.message.MessageCreateEvent
import domain.BaseMessageHandler
import domain.usecase.ResolveQrCodeUseCase
import kotlinx.coroutines.launch
import utils.FileUtils
import java.net.URL

class QrDecodeMessageHandler(
    messageCreateEventTransmitter: MessageCreateEventTransmitter, private val resolveQrCodeUseCase: ResolveQrCodeUseCase
) : BaseMessageHandler(
    messageCreateEventTransmitter
) {
    override val predicate: (MessageCreateEvent) -> Boolean
        get() = { event ->
            event.message.content.startsWith("!qrdecode")
        }

    override suspend fun setup() {
        scope.launch {
            messages.collect { event ->
                val channel = event.message.channel
                val messageContent = event.message.content
                val senderUsername = event.message.author?.mention ?: "User"

                val messageAttachments = event.message.attachments

                if (messageAttachments.size != 1 || !messageAttachments.first().isImage) {
                    channel.createMessage("${senderUsername}, please attach exactly one image file.")
                    return@collect
                }

                val fileToResolve: Attachment = messageAttachments.first()
                val inputStream = FileUtils.downloadFile(URL(fileToResolve.url))

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
    }
}