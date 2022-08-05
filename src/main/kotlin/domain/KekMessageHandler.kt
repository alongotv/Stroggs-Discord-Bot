package domain

import constants.KEK_IMAGES_ARRAY
import data.MessageCreateEventTransmitter
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class KekMessageHandler(messageCreateEventTransmitter: MessageCreateEventTransmitter) :
    BaseMessageHandler(messageCreateEventTransmitter) {

    override val predicate: (MessageCreateEvent) -> Boolean
        get() = {
            it.message.content.lowercase().contains("kek") || it.message.content.lowercase().contains("кек")
        }

    override suspend fun setup() {
        scope.launch {
            messages.collect { event ->
                val response =
                    event.message.channel.createMessage("${event.message.author?.username ?: "The user"} has provided us with a fresh KeK!")

                val kekEmoji = ReactionEmoji.Unicode("\uD83D\uDC79")

                val kekImageResponse =
                    event.message.channel.createMessage(KEK_IMAGES_ARRAY.random())
                response.addReaction(kekEmoji)
                delay(10000)
                response.delete()
                kekImageResponse.delete()
            }
        }
    }
}
