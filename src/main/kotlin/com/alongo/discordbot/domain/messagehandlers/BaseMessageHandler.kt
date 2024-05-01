package com.alongo.discordbot.domain.messagehandlers

import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseMessageHandler {
    abstract suspend fun handle(command: String, event: MessageCreateEvent)
    protected val messageHandlerScope = CoroutineScope(Dispatchers.IO)
}
