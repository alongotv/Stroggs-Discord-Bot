import constants.KEK_IMAGE_LINK
import dev.kord.core.Kord
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import kotlinx.coroutines.delay

suspend fun main() {

    val token = System.getenv("STROGG_DISCORD_BOT_KEY")
    val kord = Kord(token)



    kord.on<MessageCreateEvent> {
        if (message.author?.isBot == true) return@on
        if (message.content.lowercase().contains("kek") || message.content.lowercase().contains("кек")) {
            val response = message.channel.createMessage("${message.author?.username ?: "Юзер"} прислал нам свежеиспеченного KeKа!")

            val kekEmoji = ReactionEmoji.Unicode("\uD83D\uDC79")

            val kekImageResponse =
                message.channel.createMessage(KEK_IMAGE_LINK)
            response.addReaction(kekEmoji)

            delay(5000)
            message.delete()
            response.delete()
            kekImageResponse.delete()
        }
    }

    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}