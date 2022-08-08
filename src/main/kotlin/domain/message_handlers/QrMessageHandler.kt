package domain.message_handlers

import data.MessageCreateEventTransmitter
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.Attachment
import dev.kord.core.event.message.MessageCreateEvent
import domain.BaseMessageHandler
import domain.scenario.GenerateQrCodeFromTextScenario
import domain.usecase.ResolveQrCodeUseCase
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import utils.FileUtils
import java.net.URL

class QrMessageHandler(
    messageCreateEventTransmitter: MessageCreateEventTransmitter,
    private val generateQrCodeFromTextScenario: GenerateQrCodeFromTextScenario,
    private val resolveQrCodeUseCase: ResolveQrCodeUseCase
) : BaseMessageHandler(
    messageCreateEventTransmitter
) {
    override val predicate: (MessageCreateEvent) -> Boolean
        get() = { event ->
            event.message.content.startsWith("!qrencode") || event.message.content.startsWith("!qrdecode")
        }

    override suspend fun setup() {
        scope.launch {
            messages.collect { event ->
                val channel = event.message.channel
                val messageContent = event.message.content

                val senderUsername = event.message.author?.mention ?: "User"

                if (messageContent.length > 500) {
                    channel.createMessage("${senderUsername}, the encoded message is too long. Remove unnecessary data.")
                    return@collect
                }

                if (messageContent.contains("qrencode")) {
                    withContext(NonCancellable) {
                        val contentToEncode = messageContent.removePrefix("!qrencode").trim()
                        if (contentToEncode.isBlank()) {
                            channel.createMessage("${senderUsername}, please enter a message for further encoding.")
                            return@withContext
                        } else {
                            generateQrCodeFromTextScenario(contentToEncode, event)
                        }
                    }
                } else if (messageContent.contains("qrdecode")) {
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
                                "@${senderUsername}, the bot has found \"$resolvedQrCodeText\" encoded in your picture."
                        }
                    } catch (e: Exception) {
                        channel.createMessage("${senderUsername}, the bot was unable to find any QR codes in the provided picture")
                    }
                }
            }
        }
    }
}
