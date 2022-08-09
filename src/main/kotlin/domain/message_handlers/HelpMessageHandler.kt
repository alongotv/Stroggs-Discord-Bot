package domain.message_handlers

import data.MessageCreateEventTransmitter
import dev.kord.core.event.message.MessageCreateEvent
import domain.BaseMessageHandler

class HelpMessageHandler(messageCreateEventTransmitter: MessageCreateEventTransmitter) : BaseMessageHandler(
    messageCreateEventTransmitter
) {
    override val predicate: (MessageCreateEvent) -> Boolean
        get() = { it.message.content.startsWith("!help") }

    override suspend fun handle(event: MessageCreateEvent) {

    }
}