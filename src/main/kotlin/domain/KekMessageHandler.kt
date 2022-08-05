package domain

import constants.KEK_IMAGES_ARRAY
import data.MessageCreateEventTransmitter
import dev.kord.core.entity.ReactionEmoji
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class KekMessageHandler(private val messageCreateEventTransmitter: MessageCreateEventTransmitter) {

    suspend fun setup() {
        CoroutineScope(Dispatchers.IO).launch {
            messageCreateEventTransmitter.messagesFlow
                .filter {
                    it.message.content.lowercase().contains("kek") || it.message.content.lowercase().contains("кек")
                }.collect { event ->
                    val response =
                        event.message.channel.createMessage("${event.message.author?.username ?: "The user"} has provided us with a fresh KeK!")

                    val kekEmoji = ReactionEmoji.Unicode("\uD83D\uDC79")

                    val kekImageResponse =
                        event.message.channel.createMessage(KEK_IMAGES_ARRAY.random())
                    response.addReaction(kekEmoji)

                    delay(5000)
                    event.message.delete()
                    response.delete()
                    kekImageResponse.delete()
                }
        }
    }
}
