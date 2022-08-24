package com.alongo.discordbot.domain.message_handlers.misc

import com.alongo.discordbot.data.MessageCreateEventTransmitter
import dev.kord.core.event.message.MessageCreateEvent
import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler

class HelpMessageHandler(messageCreateEventTransmitter: MessageCreateEventTransmitter) : BaseMessageHandler(
    messageCreateEventTransmitter
) {
    override val predicate: (MessageCreateEvent) -> Boolean
        get() = { it.message.content.startsWith("!help") }

    override suspend fun handle(event: MessageCreateEvent) {

    }
}