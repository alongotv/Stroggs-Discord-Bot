import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import di.AppCompositionRoot

val compositionRoot = AppCompositionRoot.getInstance()

suspend fun main() {
    val token = System.getenv("STROGG_DISCORD_BOT_KEY")
    val kord = Kord(token)

    compositionRoot.messageHandlerFactory.generateMessageHandlers()

    kord.on<MessageCreateEvent> {
        compositionRoot.messageCreateEventTransmitter.handle(this)
    }

    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}
