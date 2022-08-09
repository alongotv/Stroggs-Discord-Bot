package domain.message_handlers.misc

import constants.KEK_IMAGES_PATHS_ARRAY
import data.MessageCreateEventTransmitter
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import domain.message_handlers.BaseMessageHandler
import kotlinx.coroutines.delay
import utils.FileUtils

class KekMessageHandler(messageCreateEventTransmitter: MessageCreateEventTransmitter) :
    BaseMessageHandler(messageCreateEventTransmitter) {

    override val predicate: (MessageCreateEvent) -> Boolean
        get() = {
            it.message.content.lowercase().contains("kek") || it.message.content.lowercase().contains("кек")
        }

    override suspend fun handle(event: MessageCreateEvent) {
        val kekEmoji = ReactionEmoji.Unicode("\uD83D\uDC79")
        val response =
            event.message.channel.createMessage("${event.message.author?.mention ?: "The user"} has provided us with a fresh KeK!")
        response.addReaction(kekEmoji)

        val filePath = KEK_IMAGES_PATHS_ARRAY.random()

        val file = FileUtils.getResourceAsStream(filePath)

        val kekImageResponse =
            event.message.channel.createMessage {
                if (file != null) {
                    this.addFile("kek_image.png", file)
                } else {
                    this.content = "Error during loading an image. Please refer to bot's administrator"
                }
            }

        delay(10000)
        response.delete()
        kekImageResponse.delete()
    }
}