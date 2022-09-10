package com.alongo.discordbot.domain.message_handlers.tts

import com.alongo.discordbot.data.MessageCreateEventTransmitter
import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import dev.kord.core.event.message.MessageCreateEvent

class TtsMessageHandler(messageCreateEventTransmitter: MessageCreateEventTransmitter): BaseMessageHandler(messageCreateEventTransmitter) {
    override val predicate: (MessageCreateEvent) -> Boolean
        get() = { it.message.content.startsWith("!tts") }

    override suspend fun handle(event: MessageCreateEvent) {
        TODO("Not yet implemented")
    }

}
