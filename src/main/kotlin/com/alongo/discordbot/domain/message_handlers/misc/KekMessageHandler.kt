package com.alongo.discordbot.domain.message_handlers.misc

import com.alongo.discordbot.constants.KEK_IMAGES_PATHS_ARRAY
import com.alongo.discordbot.data.MessageCreateEventTransmitter
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import kotlinx.coroutines.delay
import com.alongo.discordbot.utils.FileUtils
import io.ktor.client.request.forms.*
import io.ktor.utils.io.jvm.javaio.*

class KekMessageHandler : BaseMessageHandler() {
    override suspend fun handle(command: String, event: MessageCreateEvent) {
        val kekEmoji = ReactionEmoji.Unicode("\uD83D\uDC79")
        val response =
            event.message.channel.createMessage("${event.message.author?.mention ?: "The user"} has provided us with a fresh KeK!")
        response.addReaction(kekEmoji)

        val filePath = KEK_IMAGES_PATHS_ARRAY.random()

        val file = FileUtils.getResourceAsStream(filePath)

        val kekImageResponse =
            event.message.channel.createMessage {
                if (file != null) {
                    this.addFile("kek_image.png", ChannelProvider { file.toByteReadChannel() })
                } else {
                    this.content = "Error during loading an image. Please refer to bot's administrator"
                }
            }

        delay(10000)
        response.delete()
        kekImageResponse.delete()
    }
}
