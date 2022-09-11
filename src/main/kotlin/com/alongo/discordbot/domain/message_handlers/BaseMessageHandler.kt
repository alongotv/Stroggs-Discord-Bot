package com.alongo.discordbot.domain.message_handlers

import dev.kord.core.event.message.MessageCreateEvent

abstract class BaseMessageHandler {
    abstract suspend fun handle(command: String, event: MessageCreateEvent)
}
