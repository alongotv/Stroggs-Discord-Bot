package com.alongo.discordbot.domain.messagehandlers.misc

import com.alongo.discordbot.constants.KEK_IMAGES_PATHS_ARRAY
import com.alongo.discordbot.domain.messagehandlers.BaseMessageHandler
import com.alongo.discordbot.utils.FileUtils
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import io.ktor.client.request.forms.ChannelProvider
import io.ktor.util.cio.toByteReadChannel
import kotlinx.coroutines.delay

class KekMessageHandler : BaseMessageHandler() {
    override suspend fun handle(command: String, event: MessageCreateEvent) {
        val kekEmoji = ReactionEmoji.Unicode("\uD83D\uDC79")
        val response =
            event.message.channel.createMessage(
                "${event.message.author?.mention ?: "The user"} has provided us with a fresh KeK!"
            )
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

        delay(KEK_RESPONSE_DELETE_DELAY)
        response.delete()
        kekImageResponse.delete()
    }
}

private const val KEK_RESPONSE_DELETE_DELAY = 10000L